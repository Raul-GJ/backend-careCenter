package salud.servicio;

import java.util.Collection;

import salud.modelo.PlantillaFormulario;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.repositorio.EntidadNoEncontrada;

public interface IServicioFormulariosPlantilla {

	public String altaFormulario(String nombre, String descripcion);
	
	public void modificarFormulario(String id, String nombre, String descripcion) 
			throws EntidadNoEncontrada;
	
	public int agregarPregunta(String id, PreguntaEncuesta pregunta) throws EntidadNoEncontrada;
	
	public void eliminarPregunta(String id, int pos) throws EntidadNoEncontrada;
	
	public PlantillaFormulario obtenerFormulario(String id) throws EntidadNoEncontrada;
	
	public Collection<PlantillaFormulario> obtenerFormularios();
	
	public Collection<PlantillaFormulario> obtenerFormularios(Collection<String> ids);
	
	public void eliminarFormulario(String id) throws EntidadNoEncontrada;
}
