package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioMedicos;
import salud.repositorio.RepositorioPacientes;
import salud.rest.dto.usuario.MedicoDto;

@Service
@Transactional
public class ServicioMedicos implements IServicioMedicos {

	// Atributos
	
	private RepositorioMedicos repositorioMedicos;
	private RepositorioPacientes repositorioPacientes;
	
	// Constructores
	
	public ServicioMedicos(RepositorioMedicos repositorioMedicos,
			RepositorioPacientes repositorioPacientes) {
		super();
		this.repositorioMedicos = repositorioMedicos;
		this.repositorioPacientes = repositorioPacientes;
	}
	
	// Métodos
	
	@Override
	public String altaMedico(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal) {
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
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		
		MedicoFamilia medico = new MedicoFamilia(nombre, apellido1, apellido2, email, telefono, nCol, atributoTemporal);
		return repositorioMedicos.save(medico).getId();
	}

	@Override
	public void modificarMedico(String id, String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal) throws EntidadNoEncontrada {
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
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		
		MedicoFamilia medico = obtenerMedico(id);
		
		medico.setNombre(nombre);
		medico.setApellido1(apellido1);
		medico.setApellido2(apellido2);
		medico.setEmail(email);
		medico.setTelefono(telefono);
		medico.setNCol(nCol);
		medico.setAtributoTemporal(atributoTemporal);
		
		repositorioMedicos.save(medico);
	}

	@Override
	public MedicoFamilia obtenerMedico(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<MedicoFamilia> optional = repositorioMedicos.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		MedicoFamilia medico = optional.get();
		return medico;
	}

	@Override
	public Collection<MedicoDto> obtenerMedicos() {
		Collection<MedicoDto> medicos = new LinkedList<MedicoDto>();
		repositorioMedicos.findAll().forEach(m -> medicos.add(MedicoDto.from(m)));
		return medicos;
	}
	
	@Override
	public Collection<MedicoDto> obtenerMedicos(Collection<String> ids) {
		Collection<MedicoDto> medicos = new LinkedList<MedicoDto>();
		repositorioMedicos.findAllById(ids).forEach(m -> medicos.add(MedicoDto.from(m)));
		return medicos;
	}

	@Override
	public void eliminarMedico(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		repositorioMedicos.deleteById(id);
	}

	@Override
	public void agregarPacientes(String id, Collection<String> ids) throws EntidadNoEncontrada {
		MedicoFamilia medico = obtenerMedico(id);
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(ids).forEach(p -> pacientes.add(p));
		medico.agregarPacientes(pacientes);
		repositorioMedicos.save(medico);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> ids) throws EntidadNoEncontrada {
		MedicoFamilia medico = obtenerMedico(id);
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(ids).forEach(p -> pacientes.add(p));
		medico.eliminarPacientes(pacientes);
		repositorioMedicos.save(medico);
	}
}
