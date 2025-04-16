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
import salud.rest.dto.estudio.CrearEstudioDto;
import salud.rest.dto.estudio.EstudioDto;

public interface EstudiosApi {

	@Operation(summary = "Crear estudio", description = "Crea un nuevo seguimiento")
	@PostMapping
	public ResponseEntity<EstudioDto> crearEstudio(
			@Valid @RequestBody CrearEstudioDto estudioDto) throws Exception;
	
	@Operation(summary = "Modificar estudio", description = "Modifica los datos de un estudio")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarEstudio(
			@Valid @RequestBody CrearEstudioDto estudioDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega pacientes a un estudio")
	@PatchMapping("/{id}/pacientes/agregar")
	public ResponseEntity<Void> agregarPacientes(
			@Valid @RequestBody Collection<String> pacientes,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar seguimientos", description = "Agrega seguimientos a un estudio")
	@PatchMapping("/{id}/seguimientos/agregar")
	public ResponseEntity<Void> agregarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar alertas", description = "Agrega alertas a un estudio")
	@PatchMapping("/{id}/alertas/agregar")
	public ResponseEntity<Void> agregarAlertas(
			@Valid @RequestBody Collection<String> alertas,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar pacientes", description = "Elimina pacientes de un estudio")
	@PatchMapping("/{id}/pacientes/eliminar")
	public ResponseEntity<Void> eliminarPacientes(
			@Valid @RequestBody Collection<String> pacientes,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar seguimientos", description = "Elimina seguimientos de un estudio")
	@PatchMapping("/{id}/seguimientos/eliminar")
	public ResponseEntity<Void> eliminarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar alertas", description = "Elimina alertas de un estudio")
	@PatchMapping("/{id}/alertas/eliminar")
	public ResponseEntity<Void> eliminarAlertas(
			@Valid @RequestBody Collection<String> alertas,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener estudio", description = "Obtiene los datos de un estudio")
	@GetMapping("/{id}")
	public ResponseEntity<EstudioDto> obtenerEstudio(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener estudios", description = "Obtiene los datos de todos los estudios")
	@GetMapping
	public ResponseEntity<Collection<EstudioDto>> obtenerEstudios() throws Exception;
	
	@Operation(summary = "Eliminar estudio", description = "Elimina un estudio de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEstudio(
			@PathVariable String id) throws Exception;
}
