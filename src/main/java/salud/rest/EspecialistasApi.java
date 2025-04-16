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
import salud.rest.dto.usuario.EspecialistaDto;

public interface EspecialistasApi {

	@Operation(summary = "Alta especialista", description = "Da de alta un nuevo especialista")
	@PostMapping
	public ResponseEntity<EspecialistaDto> altaEspecialista(
			@Valid @RequestBody EspecialistaDto especialistaDto) throws Exception;
	
	@Operation(summary = "Modificar especialista", description = "Modifica los datos de un especialista")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarEspecialista(
			@Valid @RequestBody EspecialistaDto especialistaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega pacientes a un especialista")
	@PatchMapping("/{id}/pacientes/agregar")
	public ResponseEntity<Void> agregarPacientes(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar pacientes", description = "Elimina pacientes de un especialista")
	@PatchMapping("/{id}/pacientes/eliminar")
	public ResponseEntity<Void> eliminarPacientes(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar plantillas", description = "Agrega plantillas a un especialista")
	@PatchMapping("/{id}/plantillas/agregar")
	public ResponseEntity<Void> agregarPlantillas(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar plantillas", description = "Elimina plantillas de un especialista")
	@PatchMapping("/{id}/plantillas/eliminar")
	public ResponseEntity<Void> eliminarPlantillas(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener especialista", description = "Obtiene los datos de un especialista")
	@GetMapping("/{id}")
	public ResponseEntity<EspecialistaDto> obtenerEspecialista(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener especialistas", description = "Obtiene los datos de todos los especialistas")
	@GetMapping
	public ResponseEntity<Collection<EspecialistaDto>> obtenerEspecialistas() throws Exception;
	
	@Operation(summary = "Eliminar especialista", description = "Elimina un especialista de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEspecialista(
			@PathVariable String id) throws Exception;
}
