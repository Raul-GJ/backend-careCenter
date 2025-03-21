package salud.servicio;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Usuario;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.UsuarioDto;

public interface IServicioUsuarios {
	
	public String altaUsuario(String nombre, String apellido1, String apellido2, 
			String email, String telefono);
	
	public void modificarUsuario(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono) throws EntidadNoEncontrada;
	
	public Usuario obtenerUsuario(String id) throws EntidadNoEncontrada;
	
	public Collection<UsuarioDto> obtenerUsuarios();
	
	public Page<UsuarioDto> obtenerUsuariosPaginado(Pageable pageable);
	
	public void eliminarUsuario(String id) throws EntidadNoEncontrada;
}
