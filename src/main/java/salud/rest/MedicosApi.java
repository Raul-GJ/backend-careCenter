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
import salud.rest.dto.usuario.MedicoDto;

public interface MedicosApi {

	@Operation(summary = "Alta médico", description = "Da de alta un nuevo médico de familia")
	@PostMapping
	public ResponseEntity<MedicoDto> altaMedico(
			@Valid @RequestBody MedicoDto medicoDto);
	
	@Operation(summary = "Modificar médico", description = "Modifica los datos de un médico de familia")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarMedico(
			@Valid @RequestBody MedicoDto medicoDto,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar pacientes", description = "Agrega pacientes a un médico de familia")
	@PatchMapping("/{id}/pacientes/agregar")
	public ResponseEntity<Void> agregarPacientes(
			@Valid @RequestBody Collection<String> ids,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar pacientes", description = "Elimina pacientes de un médico de familia")
	@PatchMapping("/{id}/pacientes/eliminar")
	public ResponseEntity<Void> eliminarPacientes(
			@Valid @RequestBody Collection<String> ids,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener médico", description = "Obtiene los datos de un médico de familia")
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDto> obtenerMedico(
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener médicos", description = "Obtiene los datos de todos los médicos de familia")
	@GetMapping
	public ResponseEntity<Collection<MedicoDto>> obtenerMedicos() throws Exception;
	
	@Operation(summary = "Eliminar médico", description = "Elimina un médico de familia de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarMedico(
			@Valid @PathVariable String id) throws Exception;
}
