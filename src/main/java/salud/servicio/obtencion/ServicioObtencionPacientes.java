package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Paciente;
import salud.repositorio.RepositorioPacientes;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionPacientes implements IServicioObtencionPacientes {

	// Atributos
	
	private RepositorioPacientes repositorioPacientes;
	
	// Constructores
	
	public ServicioObtencionPacientes(RepositorioPacientes repositorioPacientes) {
		super();
		this.repositorioPacientes = repositorioPacientes;
	}
	
	// Métodos
	
	// Pacientes

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
}
