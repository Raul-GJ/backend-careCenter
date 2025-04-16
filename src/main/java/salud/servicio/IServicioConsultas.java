package salud.servicio;

import java.util.Collection;

import salud.modelo.Consulta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioConsultas {

	public String altaConsulta(String asunto, String mensaje, String emisor, String receptor)
			throws EntidadNoEncontrada;
	
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada;
	
	public Collection<Consulta> obtenerConsultas();
	
	public Collection<Consulta> obtenerConsultas(Collection<String> ids);
	
	public void eliminarConsulta(String id) throws EntidadNoEncontrada;
	
	public void responderConsulta(String id, String mensaje) throws EntidadNoEncontrada;
}
