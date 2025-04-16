package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Plantilla;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.rest.dto.formulario.PlantillaDto;
import salud.rest.dto.formulario.tipos.TipoDatoBooleanoDto;
import salud.rest.dto.formulario.tipos.TipoDatoCadenaDto;
import salud.rest.dto.formulario.tipos.TipoDatoEnumDto;
import salud.rest.dto.formulario.tipos.TipoDatoNumeralDto;
import salud.rest.dto.formulario.tipos.TipoDatoRangoDto;
import salud.servicio.IServicioPlantillas;

@RestController
@RequestMapping("/salud/api/plantillas")
public class ControladorPlantillas implements PlantillasApi {
	
	// Atributos
	
	private IServicioPlantillas servicioPlantillas;
	
	// Constructores
	
	public ControladorPlantillas(IServicioPlantillas servicioPlantillas) {
		this.servicioPlantillas = servicioPlantillas;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<PlantillaDto> crearPlantilla(
			PlantillaDto formularioDto) {
		String id = servicioPlantillas.altaPlantilla(formularioDto.getNombre(), 
				formularioDto.getDescripcion());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> modificarPlantilla(PlantillaDto formularioDto,
			String id) throws Exception {
		servicioPlantillas.modificarPlantilla(id, 
				formularioDto.getNombre(), 
				formularioDto.getDescripcion());
		return ResponseEntity.noContent().build();
	}
	
	private ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(PreguntaEncuesta pregunta, String id) throws Exception {
		int pos =  servicioPlantillas.agregarPregunta(id, pregunta);
		
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
		servicioPlantillas.eliminarPregunta(id, pos);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<PlantillaDto> obtenerPlantilla(String id) 
			throws Exception {
		PlantillaDto plantillaFormularioDto = PlantillaDto.from(
				servicioPlantillas.obtenerPlantilla(id));
		return ResponseEntity.ok(plantillaFormularioDto);
	}
	
	@Override
	public ResponseEntity<Collection<PlantillaDto>> obtenerPlantillas() 
			throws Exception {
		Collection<Plantilla> plantillas = servicioPlantillas.obtenerPlantillas();
		Collection<PlantillaDto> dtos = new LinkedList<PlantillaDto>();
		plantillas.forEach(a -> dtos.add(PlantillaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarPlantilla(String id) throws Exception {
		servicioPlantillas.eliminarPlantilla(id);
		return ResponseEntity.noContent().build();
	}
}
