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
import salud.rest.dto.formulario.RellenarFormularioDto;
import salud.rest.dto.seguimiento.CrearSeguimientoDto;
import salud.rest.dto.seguimiento.SeguimientoDto;

public interface SeguimientosApi {

	// Seguimientos
	
	@Operation(summary = "Crear seguimiento", description = "Crea un nuevo seguimiento")
	@PostMapping
	public ResponseEntity<SeguimientoDto> crearSeguimiento(
			@Valid @RequestBody CrearSeguimientoDto seguimientoDto) throws Exception;
	
	@Operation(summary = "Modificar seguimiento", description = "Modifica los datos de un seguimiento")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarSeguimiento(
			@Valid @RequestBody CrearSeguimientoDto seguimientoDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener seguimiento", description = "Obtiene los datos de un seguimiento")
	@GetMapping("/{id}")
	public ResponseEntity<SeguimientoDto> obtenerSeguimiento(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener seguimientos", description = "Obtiene los datos de todos los seguimientos")
	@GetMapping
	public ResponseEntity<Collection<SeguimientoDto>> obtenerSeguimientos() throws Exception;
	
	@Operation(summary = "Eliminar seguimiento", description = "Elimina un seguimiento de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarSeguimiento(
			@PathVariable String id) throws Exception;
	
	// Formularios
	
	@Operation(summary = "Rellenar formulario", description = "Rellena un formulario")
	@PatchMapping("/{id}/formulario")
	public ResponseEntity<Void> rellenarFormulario(
			@PathVariable String id,
			@Valid @RequestBody RellenarFormularioDto formularioDto) throws Exception;
}
