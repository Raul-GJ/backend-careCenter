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
import salud.rest.dto.asignacion.AsignacionEstudioDto;
import salud.rest.dto.asignacion.CrearAsignacionEstudioDto;
import salud.rest.dto.asignacion.ModificarAsignacionDto;
import salud.rest.dto.asignacion.ResponderInvitacionDto;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface AsignacionesApi {
	
	@Operation(summary = "Invitar especialista", description = "Envia una invitación a un especialista para participar en un estudio")
	@PostMapping
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> invitarEspecialista(
			@Valid @RequestBody CrearAsignacionEstudioDto asignacionDto) 
			throws EntidadNoEncontrada;
	
	@Operation(summary = "Responder invitacion", description = "Responde a una invitación de un especialista")
	@PatchMapping("{id}/respuesta")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> responderInvitacion(
			@PathVariable String id,
			@Valid @RequestBody ResponderInvitacionDto respuestaDto) 
			throws EntidadNoEncontrada;
	
	@Operation(summary = "Modificar asignacion", description = "Modifica los datos de una asignación")
	@PatchMapping("{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> modificarAsignacion(
			@PathVariable String id,
			@Valid @RequestBody ModificarAsignacionDto asignacionDto) 
			throws EntidadNoEncontrada;
	
	@Operation(summary = "Eliminar asignacion", description = "Elimina una asignación de la base de datos")
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarAsignacion(
			@PathVariable String id) throws EntidadNoEncontrada;
	
	@Operation(summary = "Obtener asignacion", description = "Obtiene una asignación a partir de su id")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<AsignacionEstudioDto> obtenerAsignacion(
			@PathVariable String id) 
			throws EntidadNoEncontrada;
	
	@Operation(summary = "Obtener especialistas", description = "Obtiene todos los especialistas asociados a un estudio junto con la información de la asociación")
	@GetMapping("/especialistas/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Collection<AsignacionEstudioDto>> obtenerEspecialistas(
			@PathVariable String id) 
			throws EntidadNoEncontrada;
	
	@Operation(summary = "Obtener estudios", description = "Obtiene todos los estudios asociados a un especialista junto con la información de la asociación")
	@GetMapping("/estudios/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Collection<AsignacionEstudioDto>> obtenerEstudios(
			@PathVariable String id) 
			throws EntidadNoEncontrada;
}
