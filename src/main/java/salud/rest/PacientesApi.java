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
import salud.rest.dto.usuario.CrearPacienteDto;
import salud.rest.dto.usuario.PacienteDto;

public interface PacientesApi {
	
	@Operation(summary = "Alta paciente", description = "Da de alta un nuevo paciente")
	@PostMapping
	public ResponseEntity<PacienteDto> altaPaciente(
			@Valid @RequestBody CrearPacienteDto pacienteDto) throws Exception;
	
	@Operation(summary = "Modificar paciente", description = "Modifica los datos de un paciente")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarPaciente(
			@Valid @RequestBody PacienteDto pacienteDto,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar alertas", description = "Agrega alertas a un paciente")
	@PatchMapping("/{id}/alertas/agregar")
	public ResponseEntity<Void> agregarAlertas(
			@Valid @RequestBody Collection<String> alertas,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar consultas", description = "Agrega consultas a un paciente")
	@PatchMapping("/{id}/consultas/agregar")
	public ResponseEntity<Void> agregarConsultas(
			@Valid @RequestBody Collection<String> consultas,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar especialistas", description = "Agrega especialistas a un paciente")
	@PatchMapping("/{id}/especialistas/agregar")
	public ResponseEntity<Void> agregarEspecialistas(
			@Valid @RequestBody Collection<String> especialistas,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Agregar seguimientos", description = "Agrega seguimientos a un paciente")
	@PatchMapping("/{id}/seguimientos/agregar")
	public ResponseEntity<Void> agregarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar alertas", description = "Elimina alertas de un paciente")
	@PatchMapping("/{id}/alertas/eliminar")
	public ResponseEntity<Void> eliminarAlertas(
			@Valid @RequestBody Collection<String> alertas,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar especialistas", description = "Elimina especialistas de un paciente")
	@PatchMapping("/{id}/especialistas/eliminar")
	public ResponseEntity<Void> eliminarEspecialistas(
			@Valid @RequestBody Collection<String> especialistas,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Eliminar seguimientos", description = "Elimina seguimientos de un paciente")
	@PatchMapping("/{id}/seguimientos/eliminar")
	public ResponseEntity<Void> eliminarSeguimientos(
			@Valid @RequestBody Collection<String> seguimientos,
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener paciente", description = "Obtiene los datos de un paciente")
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDto> obtenerPaciente(
			@Valid @PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener pacientes", description = "Obtiene los datos de todos los pacientes")
	@GetMapping
	public ResponseEntity<Collection<PacienteDto>> obtenerPacientes() throws Exception;
	
	@Operation(summary = "Eliminar paciente", description = "Elimina un paciente de la base de datos")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPaciente(
			@Valid @PathVariable String id) throws Exception;
}
