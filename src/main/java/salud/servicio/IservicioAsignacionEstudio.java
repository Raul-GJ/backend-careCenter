package salud.servicio;

import java.util.Collection;

import salud.modelo.AsignacionEstudio;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IservicioAsignacionEstudio {
	
	public String invitarEspecialista(String idEspecialista, String idEstudio, String rolStr) 
			throws EntidadNoEncontrada;
	
	public void responderInvitacion(String id, boolean aceptada) throws EntidadNoEncontrada;
	
	public void modificarAsignacion(String id, String rol) throws EntidadNoEncontrada;
	
	public void eliminarAsignacion(String id) throws EntidadNoEncontrada;
	
	public AsignacionEstudio obtenerAsignacion(String id) throws EntidadNoEncontrada;
	
	public Collection<AsignacionEstudio> obtenerEspecialistas(String id) throws EntidadNoEncontrada;
	
	public Collection<AsignacionEstudio> obtenerEstudios(String id) throws EntidadNoEncontrada;
}
