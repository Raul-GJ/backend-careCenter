package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioPacientes;
import salud.rest.dto.usuario.PacienteDto;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioPacientes repositorioPacientes;
	
	// Constructores
	
	public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
		super();
		this.repositorioPacientes = repositorioPacientes;
	}
	
	// Métodos
	
	// Pacientes
	
	@Override
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, MedicoFamilia medicoCabecera) {
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
		
		Paciente paciente = new Paciente(nombre, apellido1, apellido2, email, telefono, medicoCabecera);
		
		repositorioPacientes.save(paciente).getId();
		return repositorioPacientes.save(paciente).getId();
	}

	@Override
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, MedicoFamilia medicoCabecera) throws EntidadNoEncontrada {
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
		
		Optional<Paciente> optional = repositorioPacientes.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Paciente paciente = optional.get();
		
		paciente.setNombre(nombre);
		paciente.setApellido1(apellido1);
		paciente.setApellido2(apellido2);
		paciente.setEmail(email);
		paciente.setTelefono(telefono);
		paciente.setMedicoCabecera(medicoCabecera);
		
		repositorioPacientes.save(paciente);
	}

	@Override
	public Collection<PacienteDto> obtenerPacientes() throws EntidadNoEncontrada {
		Collection<PacienteDto> pacientes = new LinkedList<PacienteDto>();
		repositorioPacientes.findAll().forEach(p -> pacientes.add(PacienteDto.from(p)));
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
}
