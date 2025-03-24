package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioAlertas;
import salud.repositorio.RepositorioConsultas;
import salud.repositorio.RepositorioEspecialistas;
import salud.repositorio.RepositorioMedicos;
import salud.repositorio.RepositorioPacientes;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.dto.usuario.PacienteDto;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioPacientes repositorioPacientes;
	private RepositorioMedicos repositorioMedicos;
	private RepositorioAlertas repositorioAlertas;
	private RepositorioConsultas repositorioConsultas;
	private RepositorioEspecialistas repositorioEspecialistas;
	private RepositorioSeguimientos repositorioSeguimientos;
	
	// Constructores
	
	public ServicioPacientes(RepositorioPacientes repositorioPacientes, 
			RepositorioMedicos repositorioMedicos, RepositorioAlertas repositorioAlertas, 
			RepositorioConsultas repositorioConsultas, RepositorioEspecialistas repositorioEspecialistas, 
			RepositorioSeguimientos repositorioSeguimientos) {
		super();
		this.repositorioPacientes = repositorioPacientes;
		this.repositorioMedicos = repositorioMedicos;
		this.repositorioAlertas = repositorioAlertas;
		this.repositorioConsultas = repositorioConsultas;
		this.repositorioEspecialistas = repositorioEspecialistas;
		this.repositorioSeguimientos = repositorioSeguimientos;
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
		if (apellido2 == null || apellido2.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		if (medicoCabecera == null) {
			throw new IllegalArgumentException("El médico de cabecera no puede ser nulo");
		}
		
		Optional<MedicoFamilia> optional = repositorioMedicos.findById(medicoCabecera);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(medicoCabecera);
		}
		MedicoFamilia medico = optional.get();
		
		Paciente paciente = new Paciente(nombre, apellido1, apellido2, email, telefono, medico);
		
		repositorioPacientes.save(paciente).getId();
		return repositorioPacientes.save(paciente).getId();
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
		
		Optional<MedicoFamilia> optionalMedico = repositorioMedicos.findById(medicoCabecera);
		if (optionalMedico.isEmpty()) {
			throw new EntidadNoEncontrada(medicoCabecera);
		}
		MedicoFamilia medico = optionalMedico.get();
		
		paciente.setNombre(nombre);
		paciente.setApellido1(apellido1);
		paciente.setApellido2(apellido2);
		paciente.setEmail(email);
		paciente.setTelefono(telefono);
		paciente.setMedicoCabecera(medico);
		
		repositorioPacientes.save(paciente);
	}

	@Override
	public Collection<PacienteDto> obtenerPacientes() {
		Collection<PacienteDto> pacientes = new LinkedList<PacienteDto>();
		repositorioPacientes.findAll().forEach(p -> pacientes.add(PacienteDto.from(p)));
		return pacientes;
	}
	
	@Override
	public Collection<PacienteDto> obtenerPacientes(Collection<String> ids) {
		Collection<PacienteDto> pacientes = new LinkedList<PacienteDto>();
		repositorioPacientes.findAllById(ids).forEach(p -> pacientes.add(PacienteDto.from(p)));
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
		repositorioPacientes.deleteById(id);
	}

	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioAlertas.findAllById(alertas).forEach(a -> paciente.addAlerta(a));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioConsultas.findAllById(consultas).forEach(c -> paciente.addConsulta(c));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioEspecialistas.findAllById(especialistas).forEach(e -> paciente.addEspecialista(e));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioSeguimientos.findAllById(seguimientos).forEach(s -> paciente.addSeguimiento(s));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioAlertas.findAllById(alertas).forEach(a -> paciente.removeAlerta(a));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioEspecialistas.findAllById(especialistas).forEach(e -> paciente.removeEspecialista(e));
		repositorioPacientes.save(paciente);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		repositorioSeguimientos.findAllById(seguimientos).forEach(s -> paciente.removeSeguimiento(s));
		repositorioPacientes.save(paciente);
	}
}
