package salud.servicio;

import java.util.Collection;

import salud.modelo.NotaPaciente;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioNotas {
	
	public String altaNota(String idSanitario, String asunto, String contenido, Boolean privado)
			throws EntidadNoEncontrada;
	
	public void modificarNota(String id, String asunto, String contenido, Boolean privado) 
			throws EntidadNoEncontrada;
	
	public NotaPaciente obtenerNota(String id) throws EntidadNoEncontrada;
	
	public Collection<NotaPaciente> obtenerNotas();
	
	public void eliminarNota(String id) throws EntidadNoEncontrada;
}
