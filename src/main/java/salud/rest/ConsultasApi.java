package salud.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.rest.dto.consulta.ConsultaDto;
import salud.rest.dto.consulta.CrearConsultaDto;
import salud.rest.dto.consulta.CrearRespuestaDto;

public interface ConsultasApi {

	@Operation(summary = "Crear consulta", description = "Crea una nueva consulta dirigida a un médico")
	@PostMapping("/medicos")
	public ResponseEntity<ConsultaDto> crearConsultaMedico(
			@Valid @RequestBody CrearConsultaDto consultaDto) throws Exception;
	
	@Operation(summary = "Crear consulta", description = "Crea una nueva consulta dirigida a un especialista")
	@PostMapping("/especialistas")
	public ResponseEntity<ConsultaDto> crearConsultaEspecialista(
			@Valid @RequestBody CrearConsultaDto consultaDto) throws Exception;
	
	@Operation(summary = "Obtener consulta", description = "Obtiene los datos de una consulta")
	@GetMapping("/{id}")
	public ResponseEntity<ConsultaDto> obtenerConsulta(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener consultas", description = "Obtiene los datos de todas las consultas")
	@GetMapping
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultas() throws Exception;
	
	@Operation(summary = "Obtener consultas paciente", description = "Obtiene todas las consultas de un paciente")
	@GetMapping("/paciente/{id}")
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultasPaciente(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener consultas sanitario", description = "Obtiene todas las consultas de un personal de sanidad")
	@GetMapping("/sanitario/{id}")
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultasSanitario(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Responder consulta", description = "Crea una respuesta para una consulta")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> responderConsulta(
			@Valid @RequestBody CrearRespuestaDto respuestaDto,
			@PathVariable String id) throws Exception;
}
