package salud.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.rest.dto.consulta.ConsultaDto;
import salud.rest.dto.consulta.CrearConsultaDto;
import salud.rest.dto.consulta.CrearRespuestaDto;

public interface ConsultasApi {

	@Operation(summary = "Crear consulta", description = "Crea una nueva consulta dirigida a un sanitario")
	@PostMapping
	@PreAuthorize("hasAuthority('PACIENTE')")
	public ResponseEntity<ConsultaDto> crearConsulta(
			@Valid @RequestBody CrearConsultaDto consultaDto) throws Exception;
	
	@Operation(summary = "Obtener consulta", description = "Obtiene los datos de una consulta")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<ConsultaDto> obtenerConsulta(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener consultas", description = "Obtiene los datos de todas las consultas")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultas() throws Exception;
	
	@Operation(summary = "Obtener consultas usuario", description = "Obtiene todas las consultas de un usuario")
	@GetMapping("/usuario/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultasUsuario(
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Responder consulta", description = "Crea una respuesta para una consulta")
	@PostMapping("/{id}")
	@PreAuthorize("hasAuthority('SANITARIO')")
	public ResponseEntity<Void> responderConsulta(
			@Valid @RequestBody CrearRespuestaDto respuestaDto,
			@PathVariable String id) throws Exception;
}
