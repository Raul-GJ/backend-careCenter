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
			@Valid @RequestBody EspecialistaDto especialistaDto);
	
	@Operation(summary = "Modificar especialista", description = "Modifica los datos de un especialista")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarEspecialista(
			@Valid @RequestBody EspecialistaDto especialistaDto,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener especialista", description = "Obtiene los datos de un especialista")
	@GetMapping("/{id}")
	public ResponseEntity<EspecialistaDto> obtenerEspecialista(
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener especialistas", description = "Obtiene los datos de todos los especialistas")
	@GetMapping
	public ResponseEntity<Collection<EspecialistaDto>> obtenerEspecialistas() throws Exception;
	
	@Operation(summary = "Eliminar especialista", description = "Elimina un especialista de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEspecialista(
			@Valid @PathVariable String id) throws Exception;
}
