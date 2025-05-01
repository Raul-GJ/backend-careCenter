package salud.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import salud.rest.dto.usuario.UsuarioDto;

public interface UsuariosApi {
	
	@Operation(summary = "Obtener usuario id", description = "Obtiene los datos de un usuario por su id")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable String id) 
			 throws Exception;
	
	@Operation(summary = "Obtener usuario correo", description = "Obtiene los datos de un usuario por su correo")
	@GetMapping("/correo/{correo}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorCorreo(@PathVariable String correo) 
			 throws Exception;
	
	@Operation(summary = "Modificar usuario", description = "Modifica los datos de un usuario")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<Void> modificarUsuario(
			@Valid @RequestBody UsuarioDto usuarioDto,
			@PathVariable String id) throws Exception;
	
	@Operation(summary = "Obtener usuarios", description = "Obtiene los datos de todos los usuarios")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Collection<UsuarioDto>> obtenerUsuarios() throws Exception;
	
	@Operation(summary = "Eliminar usuario", description = "Elimina un usuario de la base de datos")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<Void> eliminarUsuario(
			@PathVariable String id) throws Exception;
}
