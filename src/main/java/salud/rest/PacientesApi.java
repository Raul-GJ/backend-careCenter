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
import salud.rest.dto.formulario.FormularioDto;
import salud.rest.dto.usuario.PacienteDto;

public interface PacientesApi {

	// Pacientes
	
	@Operation(summary = "Alta paciente", description = "Da de alta un nuevo paciente")
	@PostMapping
	public ResponseEntity<PacienteDto> altaPaciente(
			@Valid @RequestBody PacienteDto pacienteDto) throws Exception;
	
	@Operation(summary = "Modificar paciente", description = "Modifica los datos de un paciente")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarPaciente(
			@Valid @RequestBody PacienteDto pacienteDto,
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
	
	// Formularios
	
	@Operation(summary = "Crear formulario", description = "Crea un nuevo formulario")
	@PostMapping("/{idUsr}/formularios")
	public ResponseEntity<FormularioDto> crearFormulario(
			@Valid @PathVariable String idUsr,
			@Valid @RequestBody FormularioDto formularioDto) throws Exception;
	
	@Operation(summary = "Modificar formulario", description = "Modifica los datos de un formulario")
	@PatchMapping("/{idUsr}/formularios/{idForm}")
	public ResponseEntity<Void> modificarFormulario(
			@Valid @RequestBody FormularioDto formularioDto,
			@Valid @PathVariable String idUsr,
			@Valid @PathVariable int pos) throws Exception;
	
	@Operation(summary = "Obtener formulario", description = "Obtiene los datos de un formulario")
	@GetMapping("/{idUsr}/formularios/{idForm}")
	public ResponseEntity<FormularioDto> obtenerFormulario(
			@Valid @PathVariable String idUsr,
			@Valid @PathVariable int pos) throws Exception;
	
	@Operation(summary = "Obtener formularios", description = "Obtiene los datos de todos los formularios de un paciente")
	@GetMapping("/{idUsr}/formularios/")
	public ResponseEntity<Collection<FormularioDto>> obtenerFormularios(
			@Valid @PathVariable String idUsr) throws Exception;
	
	@Operation(summary = "Eliminar formulario", description = "Elimina un formulario de la base de datos")
	@DeleteMapping("/{idUsr}/formularios/{idForm}")
	public ResponseEntity<Void> eliminarFormulario(
			@Valid @PathVariable String idUsr,
			@Valid @PathVariable int pos) throws Exception;
}
