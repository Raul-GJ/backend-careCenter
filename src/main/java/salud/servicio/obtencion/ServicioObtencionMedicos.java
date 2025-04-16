package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Medico;
import salud.repositorio.RepositorioMedicos;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionMedicos implements IServicioObtencionMedicos {

	// Atributos
	
	private RepositorioMedicos repositorioMedicos;
	
	// Constructores
	
	public ServicioObtencionMedicos(RepositorioMedicos repositorioMedicos) {
		super();
		this.repositorioMedicos = repositorioMedicos;
	}
	
	// Métodos

	@Override
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Medico> optional = repositorioMedicos.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Medico medico = optional.get();
		return medico;
	}

	@Override
	public Collection<Medico> obtenerMedicos() {
		Collection<Medico> medicos = new LinkedList<Medico>();
		repositorioMedicos.findAll().forEach(m -> medicos.add(m));
		return medicos;
	}
	
	@Override
	public Collection<Medico> obtenerMedicos(Collection<String> ids) {
		Collection<Medico> medicos = new LinkedList<Medico>();
		repositorioMedicos.findAllById(ids).forEach(m -> medicos.add(m));
		return medicos;
	}
}
