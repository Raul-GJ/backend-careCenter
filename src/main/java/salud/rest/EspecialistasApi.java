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
import salud.rest.dto.usuario.EspecialistaDto;

public interface EspecialistasApi {
	
	@Operation(summary = "Modificar especialista", description = "Modifica los datos de un especialista")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> modificarEspecialista(
			@Valid @RequestBody EspecialistaDto especialistaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega pacientes a un especialista")
	@PostMapping("/{id}/pacientes/")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarPacientes(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar paciente", description = "Elimina un paciente de un especialista")
	@DeleteMapping("/{idEspecialista}/pacientes/{idPaciente}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarPaciente(
			@PathVariable String idEspecialista,
			@PathVariable String idPaciente) throws Exception;
	
	@Operation(summary = "Agregar plantillas", description = "Agrega plantillas a un especialista")
	@PostMapping("/{id}/plantillas/")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarPlantillas(
			@Valid @RequestBody Collection<String> ids,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar plantilla", description = "Elimina una plantilla de un especialista")
	@DeleteMapping("/{idEspecialista}/plantillas/{idPlantilla}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarPlantilla(
			@PathVariable String idEspecialista,
			@PathVariable String idPlantilla) throws Exception;
	
	@Operation(summary = "Obtener especialista", description = "Obtiene los datos de un especialista")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<EspecialistaDto> obtenerEspecialista(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener especialistas", description = "Obtiene los datos de todos los especialistas")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<EspecialistaDto>> obtenerEspecialistas() throws Exception;
	
	@Operation(summary = "Eliminar especialista", description = "Elimina un especialista de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarEspecialista(
			@PathVariable String id) throws Exception;
}
