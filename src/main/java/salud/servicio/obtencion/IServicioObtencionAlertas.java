package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionAlertas {
	
	public Alerta obtenerAlerta(String id) throws EntidadNoEncontrada;
	
	public Collection<Alerta> obtenerAlertas();
	
	public Collection<Alerta> obtenerAlertas(Collection<String> ids);
}
