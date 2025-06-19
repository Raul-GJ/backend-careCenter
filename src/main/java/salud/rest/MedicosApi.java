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
import salud.rest.dto.usuario.MedicoDto;

public interface MedicosApi {
	
	@Operation(summary = "Modificar médico", description = "Modifica los datos de un médico de familia")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('MEDICO')")
	public ResponseEntity<Void> modificarMedico(
			@Valid @RequestBody MedicoDto medicoDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener médico", description = "Obtiene los datos de un médico de familia")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('MEDICO')")
	public ResponseEntity<MedicoDto> obtenerMedico(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener médicos", description = "Obtiene los datos de todos los médicos de familia")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<MedicoDto>> obtenerMedicos() throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega una lista de pacientes a un médico de familia")
	@PostMapping("/{id}/pacientes")
	@PreAuthorize("hasAuthority('MEDICO')")
	public ResponseEntity<Void> agregarPacientes(
			@PathVariable String id,
			@RequestBody Collection<String> ids) throws Exception;
	
	@Operation(summary = "Eliminar paciente", description = "Elimina un paciente de un médico de familia")
	@DeleteMapping("/{idMedico}/pacientes/{idPaciente}")
	@PreAuthorize("hasAuthority('MEDICO')")
	public ResponseEntity<Void> eliminarPacientes(
			@PathVariable String idMedico,
			@PathVariable String idPaciente) throws Exception;
	
	@Operation(summary = "Eliminar médico", description = "Elimina un médico de familia de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('MEDICO')")
	public ResponseEntity<Void> eliminarMedico(
			@PathVariable String id) throws Exception;
}
