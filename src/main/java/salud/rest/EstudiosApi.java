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
import salud.rest.dto.estudio.CrearEstudioDto;
import salud.rest.dto.estudio.EstudioDto;

public interface EstudiosApi {

	@Operation(summary = "Crear estudio", description = "Crea un nuevo seguimiento")
	@PostMapping
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<EstudioDto> crearEstudio(
			@Valid @RequestBody CrearEstudioDto estudioDto) throws Exception;
	
	@Operation(summary = "Modificar estudio", description = "Modifica los datos de un estudio")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> modificarEstudio(
			@Valid @RequestBody CrearEstudioDto estudioDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega pacientes a un estudio")
	@PostMapping("/{id}/pacientes")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarPacientes(
			@Valid @RequestBody Collection<String> pacientes,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar seguimientos", description = "Agrega seguimientos a un estudio")
	@PostMapping("/{id}/seguimientos")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar alertas", description = "Agrega alertas a un estudio")
	@PostMapping("/{id}/alertas")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarAlertas(
			@Valid @RequestBody Collection<String> alertas,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar paciente", description = "Elimina un paciente de un estudio")
	@DeleteMapping("/{idEstudio}/pacientes/{idPaciente}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarPaciente(
			@PathVariable String idEstudio,
			@PathVariable String idPaciente) throws Exception;
	
	@Operation(summary = "Eliminar seguimiento", description = "Elimina un seguimiento de un estudio")
	@DeleteMapping("/{idEstudio}/seguimientos/{idSeguimiento}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarSeguimiento(
			@PathVariable String idEstudio,
			@PathVariable String idSeguimiento) throws Exception;
	
	@Operation(summary = "Eliminar alerta", description = "Elimina una alerta de un estudio")
	@DeleteMapping("/{idEstudio}/alertas/{idAlerta}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarAlerta(
			@PathVariable String idEstudio,
			@PathVariable String idAlerta) throws Exception;
	
	@Operation(summary = "Obtener estudio", description = "Obtiene los datos de un estudio")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<EstudioDto> obtenerEstudio(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener estudios", description = "Obtiene los datos de todos los estudios")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<EstudioDto>> obtenerEstudios() throws Exception;
	
	@Operation(summary = "Eliminar estudio", description = "Elimina un estudio de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarEstudio(
			@PathVariable String id) throws Exception;
}
