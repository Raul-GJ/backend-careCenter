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
import salud.rest.dto.estudio.EstudioDto;

public interface EstudiosApi {

	@Operation(summary = "Crear estudio", description = "Crea un nuevo seguimiento")
	@PostMapping
	public ResponseEntity<EstudioDto> crearEstudio(
			@Valid @RequestBody EstudioDto estudioDto) throws Exception;
	
	@Operation(summary = "Modificar estudio", description = "Modifica los datos de un estudio")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarEstudio(
			@Valid @RequestBody EstudioDto estudioDto,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener estudio", description = "Obtiene los datos de un estudio")
	@GetMapping("/{id}")
	public ResponseEntity<EstudioDto> obtenerEstudio(
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener estudios", description = "Obtiene los datos de todos los estudios")
	@GetMapping
	public ResponseEntity<Collection<EstudioDto>> obtenerEstudios() throws Exception;
	
	@Operation(summary = "Eliminar estudio", description = "Elimina un estudio de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEstudio(
			@Valid @PathVariable String id) throws Exception;
}
