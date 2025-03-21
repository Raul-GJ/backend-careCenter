package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.PlantillaFormulario;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioFormulariosPlantilla;
import salud.rest.dto.formulario.PlantillaFormularioDto;

@Service
@Transactional
public class ServicioFormulariosPlantilla implements IServicioFormulariosPlantilla {

	// Atributos
	
	private RepositorioFormulariosPlantilla repositorioFormularios;
	
	// Constructores
	
	public ServicioFormulariosPlantilla(RepositorioFormulariosPlantilla repositorioFormularios) {
		super();
		this.repositorioFormularios = repositorioFormularios;
	}
	
	// Métodos
	
	@Override
	public String altaFormulario(String nombre, String descripcion) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
		}
		
		PlantillaFormulario formulario = new PlantillaFormulario(nombre, descripcion);
		
		return repositorioFormularios.save(formulario).getId();
	}

	@Override
	public void modificarFormulario(String id, String nombre, String descripcion) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
		}
		
		Optional<PlantillaFormulario> optional = repositorioFormularios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		PlantillaFormulario formulario = optional.get();
		
		formulario.setNombre(nombre);
		formulario.setDescripcion(descripcion);
		
		repositorioFormularios.save(formulario);
	}

	@Override
	public int agregarPregunta(String id, PreguntaEncuesta tipoDatos) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (tipoDatos == null) {
			throw new IllegalArgumentException("El tipo de datos no puede ser nulo");
		}
		if (tipoDatos.getPregunta() == null || tipoDatos.getPregunta().isEmpty()) {
			throw new IllegalArgumentException("La pregunta no puede ser nula o vacía");
		}
		
		Optional<PlantillaFormulario> optional = repositorioFormularios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		PlantillaFormulario formulario = optional.get();
		int pos = formulario.addDato(tipoDatos);
		repositorioFormularios.save(formulario);
		
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
		
		Optional<PlantillaFormulario> optional = repositorioFormularios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		PlantillaFormulario formulario = optional.get();
		formulario.removeDato(pos);
		repositorioFormularios.save(formulario);
	}

	@Override
	public PlantillaFormulario obtenerFormulario(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<PlantillaFormulario> optional = repositorioFormularios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		PlantillaFormulario formulario = optional.get();
		
		return formulario;
	}

	@Override
	public Collection<PlantillaFormularioDto> obtenerFormularios() {
		Collection<PlantillaFormularioDto> formularios = new LinkedList<PlantillaFormularioDto>();
		repositorioFormularios.findAll().forEach(f -> formularios.add(PlantillaFormularioDto.from(f)));
		return formularios;
	}

	@Override
	public void eliminarFormulario(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		repositorioFormularios.deleteById(id);
	}

}
