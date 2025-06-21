package salud.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.rest.dto.formulario.PlantillaDto;
import salud.rest.dto.formulario.tipos.TipoDatoBooleanoDto;
import salud.rest.dto.formulario.tipos.TipoDatoEnumDto;
import salud.rest.dto.formulario.tipos.TipoDatoNumericoDto;
import salud.rest.dto.formulario.tipos.TipoDatoRangoDto;
import salud.rest.dto.formulario.tipos.TipoDatoTextoDto;

public interface PlantillasApi {

	@Operation(summary = "Crear plantilla", description = "Crea un nuevo formulario plantilla")
	@PostMapping
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PlantillaDto> crearPlantilla(
			@Valid @RequestBody PlantillaDto formularioDto);
	
	@Operation(summary = "Modificar plantilla", description = "Modifica los datos de un formulario plantilla")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> modificarPlantilla(
			@Valid @RequestBody PlantillaDto formularioDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta texto", description = "Agrega una pregunta de tipo texto a un formulario plantilla")
	@PostMapping("/{id}/datos/texto")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoTextoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta numerico", description = "Agrega una pregunta de tipo numerico a un formulario plantilla")
	@PostMapping("/{id}/datos/numerico")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoNumericoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta booleana", description = "Agrega una pregunta de tipo booleano a un formulario plantilla")
	@PostMapping("/{id}/datos/booleano")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoBooleanoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta enumerada", description = "Agrega una pregunta de tipo enumerado a un formulario plantilla")
	@PostMapping("/{id}/datos/enumerado")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoEnumDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pregunta rango", description = "Agrega una pregunta de tipo rango a un formulario plantilla")
	@PostMapping("/{id}/datos/rango")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<PreguntaEncuesta> agregarPreguntaFormulario(
			@Valid @RequestBody TipoDatoRangoDto preguntaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar pregunta", description = "Elimina una pregunta de un formulario plantilla")
	@DeleteMapping("/{id}/datos/{pos}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarPreguntaFormulario(
			@Valid @PathVariable int pos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener plantilla", description = "Obtiene los datos de un formulario plantilla")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<PlantillaDto> obtenerPlantilla(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener plantillas", description = "Obtiene los datos de todos los formularios plantilla")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<PlantillaDto>> obtenerPlantillas() throws Exception;
	
	@Operation(summary = "Eliminar plantilla", description = "Elimina un formulario plantilla de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarPlantilla(
			@PathVariable String id) throws Exception;
}
