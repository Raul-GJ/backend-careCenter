package salud.rest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(String id)  throws Exception {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		UsuarioDto dto = obtenerUsuarioConcreto(usuario);
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorCorreo(String correo) throws Exception {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorCorreo(correo);
		UsuarioDto dto = obtenerUsuarioConcreto(usuario);
		return ResponseEntity.ok(dto);
	}
	
	@Override
	public ResponseEntity<Collection<UsuarioDto>> obtenerUsuarios() throws Exception {
		Collection<UsuarioDto> dtos = new LinkedList<UsuarioDto>();
		servicioUsuarios.obtenerUsuarios().forEach(u -> dtos.add(obtenerUsuarioConcreto(u)));
		return ResponseEntity.ok(dtos);
	}
	
	@Override
	public ResponseEntity<Void> modificarUsuario(@Valid UsuarioDto usuarioDto, String id) throws Exception {
		servicioUsuarios.modificarUsuario(id, 
				usuarioDto.getNombre(), 
				usuarioDto.getApellidos(), 
				usuarioDto.getEmail(), 
				usuarioDto.getTelefono());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarUsuario(String id) throws Exception {
		servicioUsuarios.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> agregarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		servicioUsuarios.agregarAlertas(id, alertas);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> eliminarAlerta(String idUsuario, String idAlerta) throws Exception {
		servicioUsuarios.eliminarAlertas(idUsuario, List.of(idAlerta));
		return ResponseEntity.noContent().build();
	}
}
