package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioPacientes;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioPacientes repositorioPacientes;
	private IServicioMedicos servicioMedicos;
	private IServicioAlertas servicioAlertas;
	private IServicioConsultas servicioConsultas;
	private IServicioEspecialistas servicioEspecialistas;
	private IServicioSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ServicioPacientes(RepositorioPacientes repositorioPacientes, IServicioMedicos servicioMedicos,
			IServicioAlertas servicioAlertas, IServicioConsultas servicioConsultas,
			IServicioEspecialistas servicioEspecialistas, IServicioSeguimientos servicioSeguimientos) {
		super();
		this.repositorioPacientes = repositorioPacientes;
		this.servicioMedicos = servicioMedicos;
		this.servicioAlertas = servicioAlertas;
		this.servicioConsultas = servicioConsultas;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioSeguimientos = servicioSeguimientos;
	}
	
	// Métodos
	
	// Pacientes
	
	@Override
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String medicoCabecera) throws EntidadNoEncontrada {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		if (medicoCabecera == null) {
			throw new IllegalArgumentException("El médico de cabecera no puede ser nulo");
		}
		
		MedicoFamilia medico = servicioMedicos.obtenerMedico(medicoCabecera);
		
		Paciente paciente = new Paciente(nombre, apellido1, apellido2, email, telefono, medico);
		
		String idPaciente = repositorioPacientes.save(paciente).getId();
		servicioMedicos.agregarPaciente(medico.getId(), paciente);
		return idPaciente;
	}

	@Override
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String medicoCabecera) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (apellido2 == null || apellido2.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		if (medicoCabecera == null) {
			throw new IllegalArgumentException("El médico de cabecera no puede ser nulo");
		}
		
		Paciente paciente = obtenerPaciente(id);
		
		MedicoFamilia medico = servicioMedicos.obtenerMedico(medicoCabecera);
		MedicoFamilia anteriorMedico = paciente.getMedicoCabecera();
		if (!medico.getId().equals(anteriorMedico.getId())) {
			servicioMedicos.eliminarPaciente(medico.getId(), paciente);
			servicioMedicos.eliminarPaciente(anteriorMedico.getId(), paciente);
		}
		
		paciente.setNombre(nombre);
		paciente.setApellido1(apellido1);
		paciente.setApellido2(apellido2);
		paciente.setEmail(email);
		paciente.setTelefono(telefono);
		paciente.setMedicoCabecera(medico);
		
		repositorioPacientes.save(paciente);
	}

	@Override
	public Collection<Paciente> obtenerPacientes() {
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		repositorioPacientes.findAll().forEach(p -> pacientes.add(p));
		return pacientes;
	}
	
	@Override
	public Collection<Paciente> obtenerPacientes(Collection<String> ids) {
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(ids).forEach(p -> pacientes.add(p));
		return pacientes;
	}

	@Override
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Paciente> optional = repositorioPacientes.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Paciente paciente = optional.get();
		return paciente;
	}

	@Override
	public void eliminarPaciente(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		Paciente paciente = obtenerPaciente(id);
		
		MedicoFamilia medico = paciente.getMedicoCabecera();
		servicioMedicos.eliminarPaciente(medico.getId(), paciente);
		
		List<Especialista> especialistas = paciente.getEspecialistas();
		for (Especialista especialista : especialistas) {
			servicioEspecialistas.eliminarPaciente(especialista.getId(), paciente);
		}
		
		List<Consulta> consultas = paciente.getConsultas();
		for (Consulta consulta : consultas) {
			servicioConsultas.eliminarConsulta(consulta.getId());
		}
		
		List<Alerta> alertas = paciente.getAlertas();
		for (Alerta alerta : alertas) {
			servicioAlertas.eliminarAlerta(alerta.getId());
		}
		
		List<Seguimiento> seguimientos = paciente.getSeguimientos();
		for (Seguimiento seguimiento : seguimientos) {
			servicioSeguimientos.eliminarSeguimiento(seguimiento.getId());
		}
		
		repositorioPacientes.deleteById(id);
	}

	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		paciente.agregarAlertas(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Consulta> lista = servicioConsultas.obtenerConsultas(consultas);
		paciente.agregarConsultas(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Especialista> lista = servicioEspecialistas.obtenerEspecialistas(especialistas);
		paciente.agregarEspecialistas(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.agregarSeguimientos(lista);
		repositorioPacientes.save(paciente);
	}
	
	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarAlerta(alerta);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarConsulta(String id, Consulta consulta) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarConsulta(consulta);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarEspecialista(especialista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarSeguimiento(seguimiento);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		paciente.eliminarAlertas(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Especialista> lista = servicioEspecialistas.obtenerEspecialistas(especialistas);
		paciente.eliminarEspecialistas(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.eliminarSeguimientos(lista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarAlerta(alerta);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarEspecialista(especialista);
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarSeguimiento(seguimiento);
		repositorioPacientes.save(paciente);
	}
}
