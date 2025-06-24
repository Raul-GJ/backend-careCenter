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
import salud.rest.dto.usuario.CrearNotaPacienteDto;
import salud.rest.dto.usuario.NotaPacienteDto;

public interface NotasApi {
	
	@Operation(summary = "Crear nota", description = "Crea una nueva nota")
	@PostMapping
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> crearNota(
			@Valid @RequestBody CrearNotaPacienteDto notaDto) throws Exception;
	
	@Operation(summary = "Modificar nota", description = "Modifica los datos de una nota")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> modificarNota(
			@Valid @RequestBody NotaPacienteDto notaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener nota", description = "Obtiene los datos de una nota")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<NotaPacienteDto> obtenerNota(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener notas", description = "Obtiene los datos de todas las notas")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<NotaPacienteDto>> obtenerNotas() throws Exception;
	
	@Operation(summary = "Eliminar nota", description = "Elimina una nota de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> eliminarNota(
			@PathVariable String id) throws Exception;
}
