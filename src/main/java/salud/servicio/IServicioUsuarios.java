package salud.servicio;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Usuario;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioUsuarios {
	
	public Usuario obtenerUsuarioPorId(String id) throws EntidadNoEncontrada;
	
	public Usuario obtenerUsuarioPorCorreo(String correo) throws EntidadNoEncontrada;
	
	public Collection<Usuario> obtenerUsuarios() throws EntidadNoEncontrada;
	
	public void modificarUsuario(String id, String nombre, String apellidos, 
			String email, String telefono) throws EntidadNoEncontrada;
	
	public void eliminarUsuario(String id) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String idUsuario, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String idUsuario, String idAlerta) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String idUsuario, Alerta alerta) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String idUsuario, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String idUsuario, String idAlerta) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String idUsuario, Alerta alerta) throws EntidadNoEncontrada;
}
