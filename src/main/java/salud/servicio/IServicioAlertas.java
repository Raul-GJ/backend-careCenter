package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import salud.modelo.Alerta;
import salud.repositorio.EntidadNoEncontrada;

public interface IServicioAlertas {

	public String altaAlerta(String asunto, String mensaje, LocalDateTime fecha);
	
	public void modificarAlerta(String id, String asunto, String mensaje, LocalDateTime fecha) 
			throws EntidadNoEncontrada;
	
	public Alerta obtenerAlerta(String id) throws EntidadNoEncontrada;
	
	public Collection<Alerta> obtenerAlertas();
	
	public Collection<Alerta> obtenerAlertas(Collection<String> ids);
	
	public void eliminarAlerta(String id) throws EntidadNoEncontrada;
}
