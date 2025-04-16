package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Estudio;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionEstudios {

	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada;
	
	public Collection<Estudio> obtenerEstudios();
	
	public Collection<Estudio> obtenerEstudios(Collection<String> ids);
}
