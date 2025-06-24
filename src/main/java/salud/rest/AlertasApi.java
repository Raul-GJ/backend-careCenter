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
import salud.rest.dto.alerta.AlertaDto;
import salud.rest.dto.alerta.CrearAlertaDto;

public interface AlertasApi {

	@Operation(summary = "Crear alerta", description = "Crea una nueva alerta")
	@PostMapping
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<AlertaDto> crearAlerta(
			@Valid @RequestBody CrearAlertaDto alertaDto) throws Exception;
	
	@Operation(summary = "Modificar alerta", description = "Modifica los datos de una alerta")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> modificarAlerta(
			@Valid @RequestBody AlertaDto alertaDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Leer alerta", description = "Pasa una alerta a estado leido")
	@PatchMapping("/{id}/leer")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<Void> leerAlerta(@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener alerta", description = "Obtiene los datos de una alerta")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<AlertaDto> obtenerAlerta(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener alertas", description = "Obtiene los datos de todas las alertas")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<AlertaDto>> obtenerAlertas() throws Exception;
	
	@Operation(summary = "Eliminar alerta", description = "Elimina una alerta de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> eliminarAlerta(
			@PathVariable String id) throws Exception;
}
