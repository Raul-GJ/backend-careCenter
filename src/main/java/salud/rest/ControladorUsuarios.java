package salud.rest;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import salud.auth.JwtUtils;
import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.Usuario;
import salud.rest.dto.usuario.EspecialistaDto;
import salud.rest.dto.usuario.MedicoDto;
import salud.rest.dto.usuario.PacienteDto;
import salud.rest.dto.usuario.UsuarioDto;
import salud.servicio.IServicioUsuarios;

@RestController
@RequestMapping("/salud/api/usuarios")
public class ControladorUsuarios implements UsuariosApi {

	// Atributos
	
	private IServicioUsuarios servicioUsuarios;
	
	// Constructores
	
	public ControladorUsuarios(IServicioUsuarios servicioUsuarios) {
		super();
		this.servicioUsuarios = servicioUsuarios;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<UsuarioDto> obtenerUsuario(@Valid String id) throws Exception {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		UsuarioDto usuarioDto = new UsuarioDto();
		
		// Solo para debug, el admin tiene acceso a todos los datos de cualquier usuario
		
		if (JwtUtils.isAdmin()) {
			usuarioDto = obtenerUsuarioConcreto(usuario);
			return ResponseEntity.ok(usuarioDto);
		}
		
		// Comprobaciones paciente
		
		if (usuario instanceof Paciente) {
			Paciente paciente = (Paciente) usuario;
			if (JwtUtils.isPaciente()) {
				// El paciente no debe poder ver las notas privadas que le ponen los especialistas
				if (JwtUtils.getIdUsuario().equals(id)) {
					usuarioDto = PacienteDto.construirSinNotasPrivadas(paciente);
				} else {
					// Un paciente no tiene acceso a la información de otros pacientes
					return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
				}
			} else {
				usuarioDto = PacienteDto.from(paciente);
			}
		}
		
		// Comprobaciones médico
		
		if (usuario instanceof Medico) {
			Medico medico = (Medico) usuario;
			if (JwtUtils.getIdUsuario().equals(id)) {
				// Solo el porpio médico tiene acceso a toda su información
				usuarioDto = MedicoDto.from(medico);
			} else {
				// Los demás solo tienen acceso a una parte
				usuarioDto = MedicoDto.construirMedicoPublico(medico);
			}
		}
		
		// Comprobaciones especialista
		
		if (usuario instanceof Especialista) {
			Especialista especialista = (Especialista) usuario;
			if (JwtUtils.getIdUsuario().equals(id)) {
				// Solo el porpio especialista tiene acceso a toda su información
				usuarioDto = EspecialistaDto.from(especialista);
			} else {
				// Los demás solo tienen acceso a una parte
				usuarioDto = EspecialistaDto.construirEspecialistaPublico(especialista);
			}
		}

		return ResponseEntity.ok(usuarioDto);
	}
	
	
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(String id)  throws Exception {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		UsuarioDto dto = obtenerUsuarioConcreto(usuario);
		return ResponseEntity.ok(dto);
	}
	
	private UsuarioDto obtenerUsuarioConcreto(Usuario usuario) {
		UsuarioDto dto = new UsuarioDto();
		if (usuario instanceof Paciente) {
			Paciente paciente = (Paciente) usuario;
			dto = PacienteDto.from(paciente);
		} else if (usuario instanceof Medico) {
			Medico medico = (Medico) usuario;
			dto = MedicoDto.from(medico);
		} if (usuario instanceof Especialista) {
			Especialista especialista = (Especialista) usuario;
			dto = EspecialistaDto.from(especialista);
			
		}
		return dto;
	}
	
	@Override
	public ResponseEntity<Collection<UsuarioDto>> obtenerUsuarios() throws Exception {
		Collection<UsuarioDto> dtos = new LinkedList<UsuarioDto>();
		servicioUsuarios.obtenerUsuarios().forEach(u -> dtos.add(obtenerUsuarioConcreto(u)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarUsuario(String id) throws Exception {
		servicioUsuarios.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
