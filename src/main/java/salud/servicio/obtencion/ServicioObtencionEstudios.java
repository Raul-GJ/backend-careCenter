package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Estudio;
import salud.repositorio.RepositorioEstudios;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionEstudios implements IServicioObtencionEstudios {
	
	// Atributos
	
	private RepositorioEstudios repositorioEstudios;
	
	// Constructores
	
	public ServicioObtencionEstudios(RepositorioEstudios repositorioEstudios) {
		super();
		this.repositorioEstudios = repositorioEstudios;
	}
	
	// Métodos

	@Override
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		
		return estudio;
	}
	
	@Override
	public Collection<Estudio> obtenerEstudios() {
		Collection<Estudio> estudios = new LinkedList<Estudio>();
		repositorioEstudios.findAll().forEach(estudio -> estudios.add(estudio));
		return estudios;
	}
	
	@Override
	public Collection<Estudio> obtenerEstudios(Collection<String> ids) {
		Collection<Estudio> estudios = new LinkedList<Estudio>();
		repositorioEstudios.findAllById(ids).forEach(estudio -> estudios.add(estudio));
		return estudios;
	}
}
