package salud.servicio;

import java.util.Collection;

import salud.modelo.Especialista;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.EspecialistaDto;

public interface IServicioEspecialistas {

	// Especialistas
	
	public String altaEspecialista(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String especialidad);
	
	public void modificarEspecialista(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String nCol, String especialidad) throws EntidadNoEncontrada;
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<EspecialistaDto> obtenerEspecialistas() throws EntidadNoEncontrada;
	
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada;
	
}
