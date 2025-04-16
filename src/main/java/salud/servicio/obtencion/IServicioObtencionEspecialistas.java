package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Especialista;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionEspecialistas {
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<Especialista> obtenerEspecialistas();
	
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids);
	
}
