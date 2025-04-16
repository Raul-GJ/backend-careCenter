package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Plantilla;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionPlantillas {
	
	public Plantilla obtenerPlantilla(String id) throws EntidadNoEncontrada;
	
	public Collection<Plantilla> obtenerPlantillas();
	
	public Collection<Plantilla> obtenerPlantillas(Collection<String> ids);
}
