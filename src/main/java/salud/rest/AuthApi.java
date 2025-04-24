package salud.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.rest.dto.auth.LoginDto;
import salud.rest.dto.usuario.CrearEspecialistaDto;
import salud.rest.dto.usuario.CrearMedicoDto;
import salud.rest.dto.usuario.CrearPacienteDto;

public interface AuthApi {
	
	@Operation(summary = "Login", description = "Sirve para hacer login a una cuenta de usuario")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@Valid @RequestBody LoginDto dto,
			HttpServletResponse response);
	
	@Operation(summary = "Alta especialista", description = "Da de alta un nuevo especialista")
	@PostMapping("/registro/especialista")
	public ResponseEntity<Void> altaEspecialista(
			@Valid @RequestBody CrearEspecialistaDto especialistaDto) throws Exception;
	
	@Operation(summary = "Alta médico", description = "Da de alta un nuevo médico de familia")
	@PostMapping("/registro/medico")
	public ResponseEntity<Void> altaMedico(
			@Valid @RequestBody CrearMedicoDto medicoDto);
	
	@Operation(summary = "Alta paciente", description = "Da de alta un nuevo paciente")
	@PostMapping("/registro/paciente")
	public ResponseEntity<Void> altaPaciente(
			@Valid @RequestBody CrearPacienteDto pacienteDto) throws Exception;
	
	@Operation(summary = "Logout", description = "Sirve para hacer logout de una cuenta de usuario")
	@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(
			HttpServletRequest request, 
			HttpServletResponse response);
}
