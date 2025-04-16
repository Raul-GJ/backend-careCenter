package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Plantilla;
import salud.repositorio.RepositorioPlantillas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionPlantillas implements IServicioObtencionPlantillas {

	// Atributos
	
	private RepositorioPlantillas repositorioPlantillas;
	
	// Constructores
	
	public ServicioObtencionPlantillas(RepositorioPlantillas repositorioPlantillas) {
		super();
		this.repositorioPlantillas = repositorioPlantillas;
	}
	
	// Métodos

	@Override
	public Plantilla obtenerPlantilla(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Plantilla> optional = repositorioPlantillas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Plantilla formulario = optional.get();
		
		return formulario;
	}

	@Override
	public Collection<Plantilla> obtenerPlantillas() {
		Collection<Plantilla> formularios = new LinkedList<Plantilla>();
		repositorioPlantillas.findAll().forEach(f -> formularios.add(f));
		return formularios;
	}
	
	@Override
	public Collection<Plantilla> obtenerPlantillas(Collection<String> ids) {
		Collection<Plantilla> formularios = new LinkedList<Plantilla>();
		repositorioPlantillas.findAllById(ids).forEach(f -> formularios.add(f));
		return formularios;
	}
}
