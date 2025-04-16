package salud.servicio;

import java.util.Collection;

import salud.modelo.Plantilla;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioPlantillas {

	public String altaPlantilla(String nombre, String descripcion);
	
	public void modificarPlantilla(String id, String nombre, String descripcion) 
			throws EntidadNoEncontrada;
	
	public int agregarPregunta(String id, PreguntaEncuesta pregunta) throws EntidadNoEncontrada;
	
	public void eliminarPregunta(String id, int pos) throws EntidadNoEncontrada;
	
	public Plantilla obtenerPlantilla(String id) throws EntidadNoEncontrada;
	
	public Collection<Plantilla> obtenerPlantillas();
	
	public Collection<Plantilla> obtenerPlantillas(Collection<String> ids);
	
	public void eliminarPlantilla(String id) throws EntidadNoEncontrada;
}
