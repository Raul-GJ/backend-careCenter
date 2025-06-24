package salud.servicio;

import java.util.Collection;

import salud.modelo.Usuario;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioUsuarios {
	
	public Usuario obtenerUsuarioPorId(String id) throws EntidadNoEncontrada;
	
	public Usuario obtenerUsuarioPorCorreo(String correo) throws EntidadNoEncontrada;
	
	public Collection<Usuario> obtenerUsuarios() throws EntidadNoEncontrada;
	
	public void modificarUsuario(String id, String nombre, String apellidos, 
			String email, String telefono) throws EntidadNoEncontrada;
	
	public void eliminarUsuario(String id) throws EntidadNoEncontrada;
}
