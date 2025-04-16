package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Seguimiento;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionSeguimientos {
	
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada;
	
	public Collection<Seguimiento> obtenerSeguimientos();
	
	public Collection<Seguimiento> obtenerSeguimientos(Collection<String> ids);
}
