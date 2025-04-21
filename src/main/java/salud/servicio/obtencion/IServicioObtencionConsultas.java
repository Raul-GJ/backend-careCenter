package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Consulta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionConsultas {
	
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada;
	
	public Collection<Consulta> obtenerConsultas();
	
	public Collection<Consulta> obtenerConsultas(Collection<String> ids);
	
	public Collection<Consulta> obtenerConsultasPaciente(String id);
	
	public Collection<Consulta> obtenerConsultasSanitario(String id);
}
