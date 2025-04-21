package salud.servicio;

import java.util.Collection;

import salud.modelo.Consulta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioConsultas {

	public String altaConsultaMedico(String asunto, String mensaje, String emisor, String receptor)
			throws EntidadNoEncontrada;
	
	public String altaConsultaEspecialista(String asunto, String mensaje, String emisor, String receptor)
			throws EntidadNoEncontrada;
	
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada;
	
	public Collection<Consulta> obtenerConsultas();
	
	public Collection<Consulta> obtenerConsultas(Collection<String> ids);
	
	public Collection<Consulta> obtenerConsultasPaciente(String id);
	
	public Collection<Consulta> obtenerConsultasSanitario(String id);
	
	public void eliminarConsulta(String id) throws EntidadNoEncontrada;
	
	public void responderConsulta(String id, String mensaje) throws EntidadNoEncontrada;
}
