package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;

public interface IServicioSeguimientos {

	public String altaSeguimiento(LocalDateTime fecha, LocalDateTime plazo,  String plantilla)
		throws EntidadNoEncontrada;
	
	public void modificarSeguimiento(String id, LocalDateTime fecha, LocalDateTime plazo,
			String plantilla) throws EntidadNoEncontrada;
	
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada;
	
	public Collection<Seguimiento> obtenerSeguimientos();
	
	public Collection<Seguimiento> obtenerSeguimientos(Collection<String> ids);
	
	public void eliminarSeguimiento(String id) throws EntidadNoEncontrada;
	
	public void rellenarFormulario(String id, List<String> respuestas) throws EntidadNoEncontrada;
}
