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
import salud.rest.dto.alerta.AlertaDto;

public interface AlertasApi {

	@Operation(summary = "Crear alerta", description = "Crea una nueva alerta")
	@PostMapping
	public ResponseEntity<AlertaDto> crearAlerta(
			@Valid @RequestBody AlertaDto alertaDto);
	
	@Operation(summary = "Modificar alerta", description = "Modifica los datos de una alerta")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarAlerta(
			@Valid @RequestBody AlertaDto alertaDto,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener alerta", description = "Obtiene los datos de una alerta")
	@GetMapping("/{id}")
	public ResponseEntity<AlertaDto> obtenerAlerta(
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener alertas", description = "Obtiene los datos de todas las alertas")
	@GetMapping
	public ResponseEntity<Collection<AlertaDto>> obtenerAlertas() throws Exception;
	
	@Operation(summary = "Eliminar alerta", description = "Elimina una alerta de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarAlerta(
			@Valid @PathVariable String id) throws Exception;
}
