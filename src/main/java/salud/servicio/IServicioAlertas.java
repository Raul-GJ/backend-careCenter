package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import salud.modelo.Alerta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioAlertas {

	public String altaAlerta(String idEmisor, String idReceptor, boolean generadaAutomaticamente, 
			String asunto, String mensaje, LocalDateTime fecha) throws EntidadNoEncontrada;
	
	public void modificarAlerta(String id, String asunto, String mensaje, LocalDateTime fecha) 
			throws EntidadNoEncontrada;
	
	public void leerAlerta(String id) throws EntidadNoEncontrada;
	
	public Alerta obtenerAlerta(String id) throws EntidadNoEncontrada;
	
	public Collection<Alerta> obtenerAlertas();
	
	public Collection<Alerta> obtenerAlertas(Collection<String> ids);
	
	public void eliminarAlerta(String id) throws EntidadNoEncontrada;
}
