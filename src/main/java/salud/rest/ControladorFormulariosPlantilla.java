package salud.rest;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.encuesta.PreguntaEncuesta;
import salud.rest.dto.formulario.PlantillaFormularioDto;
import salud.rest.dto.formulario.tipos.TipoDatoBooleanoDto;
import salud.rest.dto.formulario.tipos.TipoDatoCadenaDto;
import salud.rest.dto.formulario.tipos.TipoDatoEnumDto;
import salud.rest.dto.formulario.tipos.TipoDatoNumeralDto;
import salud.rest.dto.formulario.tipos.TipoDatoRangoDto;
import salud.servicio.IServicioFormulariosPlantilla;

@RestController
@RequestMapping("/salud/api/formularios/plantillas")
public class ControladorFormulariosPlantilla implements FormulariosPlantillaApi {
	
	// Atributos
	
	private IServicioFormulariosPlantilla servicioFormulariosPlantilla;
	
	// Constructores
	
	public ControladorFormulariosPlantilla(IServicioFormulariosPlantilla servicioFormulariosPlantilla) {
		this.servicioFormulariosPlantilla = servicioFormulariosPlantilla;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<PlantillaFormularioDto> crearPlantillaFormulario(
			PlantillaFormularioDto formularioDto) {
		String id = servicioFormulariosPlantilla.altaFormulario(formularioDto.getNombre(), 
				formularioDto.getDescripcion());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> modificarPlantillaFormulario(PlantillaFormularioDto formularioDto,
			String id) throws Exception {
		servicioFormulariosPlantilla.modificarFormulario(id, 
				formularioDto.getNombre(), 
				formularioDto.getDescripcion());
		return ResponseEntity.noContent().build();
	}
	
	private ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(PreguntaEncuesta pregunta, String id) throws Exception {
		int pos =  servicioFormulariosPlantilla.agregarPregunta(id, pregunta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id + "/datos/" + pos).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(TipoDatoCadenaDto preguntaDto,
			String id) throws Exception {
		return agregarPreguntaFormulario(preguntaDto.toPreguntaEncuesta(), id);
	}
	
	@Override
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(TipoDatoNumeralDto preguntaDto,
			String id) throws Exception {
		return agregarPreguntaFormulario(preguntaDto.toPreguntaEncuesta(), id);
	}
	
	@Override
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(TipoDatoBooleanoDto preguntaDto,
			String id) throws Exception {
		return agregarPreguntaFormulario(preguntaDto.toPreguntaEncuesta(), id);
	}
	
	@Override
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(TipoDatoEnumDto preguntaDto, String id)
			throws Exception {
		return agregarPreguntaFormulario(preguntaDto.toPreguntaEncuesta(), id);
	}
	
	@Override
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(TipoDatoRangoDto preguntaDto, String id)
			throws Exception {
		return agregarPreguntaFormulario(preguntaDto.toPreguntaEncuesta(), id);
	}
	
	@Override
	public ResponseEntity<Void> eliminarPreguntaFormulario(int pos, String id) throws Exception {
		servicioFormulariosPlantilla.eliminarPregunta(id, pos);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<PlantillaFormularioDto> obtenerPlantillaFormulario(String id) 
			throws Exception {
		PlantillaFormularioDto plantillaFormularioDto = PlantillaFormularioDto.from(
				servicioFormulariosPlantilla.obtenerFormulario(id));
		return ResponseEntity.ok(plantillaFormularioDto);
	}
	
	@Override
	public ResponseEntity<Collection<PlantillaFormularioDto>> obtenerFormulariosPlantilla(
			Collection<String> ids) throws Exception {
		if (ids == null || ids.isEmpty()) {
			Collection<PlantillaFormularioDto> formularios = servicioFormulariosPlantilla.obtenerFormularios();
			return ResponseEntity.ok(formularios);
		}
		Collection<PlantillaFormularioDto> formularios = servicioFormulariosPlantilla.obtenerFormularios(ids);
		return ResponseEntity.ok(formularios);
	}

	@Override
	public ResponseEntity<Void> eliminarPlantillaFormulario(String id) throws Exception {
		servicioFormulariosPlantilla.eliminarFormulario(id);
		return ResponseEntity.noContent().build();
	}
}
