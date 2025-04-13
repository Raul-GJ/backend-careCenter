package salud.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.rest.dto.formulario.PlantillaFormularioDto;
import salud.rest.dto.formulario.tipos.TipoDatoBooleanoDto;
import salud.rest.dto.formulario.tipos.TipoDatoCadenaDto;
import salud.rest.dto.formulario.tipos.TipoDatoEnumDto;
import salud.rest.dto.formulario.tipos.TipoDatoNumeralDto;
import salud.rest.dto.formulario.tipos.TipoDatoRangoDto;

public interface FormulariosPlantillaApi {

	@Operation(summary = "Crear formulario plantilla", description = "Crea un nuevo formulario plantilla")
	@PostMapping
	public ResponseEntity<PlantillaFormularioDto> crearPlantillaFormulario(
			@Valid @RequestBody PlantillaFormularioDto formularioDto);
	
	@Operation(summary = "Modificar formulario plantilla", description = "Modifica los datos de un formulario plantilla")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarPlantillaFormulario(
			@Valid @RequestBody PlantillaFormularioDto formularioDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta cadena", description = "Agrega una pregunta de tipo cadena a un formulario plantilla")
	@PostMapping("/{id}/datos/cadena")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoCadenaDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta numeral", description = "Agrega una pregunta de tipo numeral a un formulario plantilla")
	@PostMapping("/{id}/datos/numeral")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoNumeralDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta booleana", description = "Agrega una pregunta de tipo booleano a un formulario plantilla")
	@PostMapping("/{id}/datos/booleano")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoBooleanoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta enumerada", description = "Agrega una pregunta de tipo enumerado a un formulario plantilla")
	@PostMapping("/{id}/datos/enumerado")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoEnumDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta rango", description = "Agrega una pregunta de tipo rango a un formulario plantilla")
	@PostMapping("/{id}/datos/rango")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoRangoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar pregunta", description = "Elimina una pregunta de un formulario plantilla")
	@DeleteMapping("/{id}/datos/{pos}")
	public ResponseEntity<Void> eliminarPreguntaFormulario(
			@Valid @PathVariable int pos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener formulario plantilla", description = "Obtiene los datos de un formulario plantilla")
	@GetMapping("/{id}")
	public ResponseEntity<PlantillaFormularioDto> obtenerPlantillaFormulario(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener formularios plantilla", description = "Obtiene los datos de todos los formularios plantilla")
	@GetMapping
	public ResponseEntity<Collection<PlantillaFormularioDto>> obtenerFormulariosPlantilla() throws Exception;
	
	@Operation(summary = "Eliminar formulario plantilla", description = "Elimina un formulario plantilla de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPlantillaFormulario(
			@PathVariable String id) throws Exception;
}
