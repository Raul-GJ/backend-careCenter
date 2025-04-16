package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Especialista;
import salud.repositorio.RepositorioEspecialistas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionEspecialistas implements IServicioObtencionEspecialistas {

	// Atributos
	
	private RepositorioEspecialistas repositorioEspecialistas;
	
	// Constructores
	
	public ServicioObtencionEspecialistas(RepositorioEspecialistas repositorioEspecialistas) {
		super();
		this.repositorioEspecialistas = repositorioEspecialistas;
	}
	
	// Métodos

	@Override
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Especialista> optional = repositorioEspecialistas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Especialista especialista = optional.get();
		return especialista;
	}

	@Override
	public Collection<Especialista> obtenerEspecialistas() {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		repositorioEspecialistas.findAll().forEach(e -> especialistas.add(e));
		return especialistas;
	}
	
	@Override
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids) {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		repositorioEspecialistas.findAllById(ids).forEach(e -> especialistas.add(e));
		return especialistas;
	}
}
