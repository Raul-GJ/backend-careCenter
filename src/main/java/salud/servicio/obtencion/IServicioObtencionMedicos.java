package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Medico;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionMedicos {
	
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada;
	
	public Collection<Medico> obtenerMedicos();
	
	public Collection<Medico> obtenerMedicos(Collection<String> ids);
}
