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
import salud.rest.dto.usuario.PacienteDto;

public interface PacientesApi {
	
	@Operation(summary = "Modificar paciente", description = "Modifica los datos de un paciente")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('PACIENTE')")
	public ResponseEntity<Void> modificarPaciente(
			@Valid @RequestBody PacienteDto pacienteDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar seguimientos", description = "Agrega seguimientos a un paciente")
	@PostMapping("/{id}/seguimientos")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> agregarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar seguimiento", description = "Elimina un seguimiento de un paciente")
	@DeleteMapping("/{idPaciente}/seguimientos/{idSeguimiento}")
	@PreAuthorize("hasAuthority('ESPECIALISTA')")
	public ResponseEntity<Void> eliminarSeguimiento(
			@PathVariable String idPaciente,
			@PathVariable String idSeguimiento) throws Exception;
	
	@Operation(summary = "Obtener paciente", description = "Obtiene los datos de un paciente")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<PacienteDto> obtenerPaciente(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener pacientes", description = "Obtiene los datos de todos los pacientes")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<PacienteDto>> obtenerPacientes() throws Exception;
	
	@Operation(summary = "Eliminar paciente", description = "Elimina un paciente de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('PACIENTE')")
	public ResponseEntity<Void> eliminarPaciente(
			@PathVariable String id) throws Exception;
}
