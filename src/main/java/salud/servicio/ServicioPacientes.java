package salud.servicio;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioPacientes;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionAlertas;
import salud.servicio.obtencion.IServicioObtencionEspecialistas;
import salud.servicio.obtencion.IServicioObtencionPacientes;
import salud.servicio.obtencion.IServicioObtencionSeguimientos;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioPacientes repositorioPacientes;
	private IServicioObtencionPacientes servicioPacientes;
	private IServicioMedicos servicioMedicos;
	private IServicioObtencionAlertas servicioAlertas;
	private IServicioObtencionEspecialistas servicioEspecialistas;
	private IServicioObtencionSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ServicioPacientes(RepositorioPacientes repositorioPacientes, IServicioObtencionPacientes servicioPacientes,
			IServicioMedicos servicioMedicos, IServicioObtencionAlertas servicioAlertas,
			IServicioObtencionEspecialistas servicioEspecialistas,
			IServicioObtencionSeguimientos servicioSeguimientos) {
		super();
		this.repositorioPacientes = repositorioPacientes;
		this.servicioPacientes = servicioPacientes;
		this.servicioMedicos = servicioMedicos;
		this.servicioAlertas = servicioAlertas;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioSeguimientos = servicioSeguimientos;
	}
	
	// Métodos
	
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
		if (!ValidadorEmail.esValido(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (medicoCabecera == null) {
			throw new IllegalArgumentException("El médico de cabecera no puede ser nulo");
		}
		
		Medico medico = servicioMedicos.obtenerMedico(medicoCabecera);
		
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
		if (!ValidadorEmail.esValido(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (medicoCabecera == null) {
			throw new IllegalArgumentException("El médico de cabecera no puede ser nulo");
		}
		
		Paciente paciente = obtenerPaciente(id);
		
		Medico medico = servicioMedicos.obtenerMedico(medicoCabecera);
		Medico anteriorMedico = paciente.getMedicoCabecera();
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
	public void eliminarPaciente(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		obtenerPaciente(id);
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

	@Override
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada {
		return servicioPacientes.obtenerPaciente(id);
	}

	@Override
	public Collection<Paciente> obtenerPacientes(Collection<String> ids) {
		return servicioPacientes.obtenerPacientes(ids);
	}

	@Override
	public Collection<Paciente> obtenerPacientes() {
		return servicioPacientes.obtenerPacientes();
	}
}
