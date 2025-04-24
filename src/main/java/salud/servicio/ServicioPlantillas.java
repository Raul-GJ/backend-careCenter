package salud.servicio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Plantilla;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.repositorio.RepositorioPlantillas;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionPlantillas;

@Service
@Transactional
public class ServicioPlantillas implements IServicioPlantillas {

	// Atributos
	
	private RepositorioPlantillas repositorioPlantillas;
	private IServicioObtencionPlantillas servicioPlantillas;
	
	// Constructores
	
	public ServicioPlantillas(RepositorioPlantillas repositorioPlantillas,
			IServicioObtencionPlantillas servicioPlantillas) {
		super();
		this.repositorioPlantillas = repositorioPlantillas;
		this.servicioPlantillas = servicioPlantillas;
	}
	
	// Métodos
	
	@Override
	public String altaPlantilla(String nombre, String descripcion) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
		}
		
		Plantilla formulario = new Plantilla(nombre, descripcion);
		
		return repositorioPlantillas.save(formulario).getId();
	}

	@Override
	public void modificarPlantilla(String id, String nombre, String descripcion) throws EntidadNoEncontrada {
		Plantilla formulario = obtenerPlantilla(id);
		
		if (nombre != null && !nombre.isBlank())
			formulario.setNombre(nombre);
		if (descripcion != null && !descripcion.isBlank())
			formulario.setDescripcion(descripcion);
		
		repositorioPlantillas.save(formulario);
	}

	@Override
	public int agregarPregunta(String id, PreguntaEncuesta pregunta) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (pregunta == null) {
			throw new IllegalArgumentException("El tipo de datos no puede ser nulo");
		}
		if (pregunta.getPregunta() == null || pregunta.getPregunta().isEmpty()) {
			throw new IllegalArgumentException("La pregunta no puede ser nula o vacía");
		}
		
		Optional<Plantilla> optional = repositorioPlantillas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Plantilla formulario = optional.get();
		int pos = formulario.addPregunta(pregunta);
		repositorioPlantillas.save(formulario);
		
		return pos;
	}

	@Override
	public void eliminarPregunta(String id, int pos) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (pos < 0) {
			throw new IllegalArgumentException("La posición no puede ser negativa");
		}
		
		Optional<Plantilla> optional = repositorioPlantillas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Plantilla formulario = optional.get();
		formulario.removePregunta(pos);
		repositorioPlantillas.save(formulario);
	}

	@Override
	public void eliminarPlantilla(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		repositorioPlantillas.deleteById(id);
	}

	@Override
	public Plantilla obtenerPlantilla(String id) throws EntidadNoEncontrada {
		return servicioPlantillas.obtenerPlantilla(id);
	}

	@Override
	public Collection<Plantilla> obtenerPlantillas() {
		return servicioPlantillas.obtenerPlantillas();
	}

	@Override
	public Collection<Plantilla> obtenerPlantillas(Collection<String> ids) {
		return servicioPlantillas.obtenerPlantillas(ids);
	}
}
