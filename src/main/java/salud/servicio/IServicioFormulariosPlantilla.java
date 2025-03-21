package salud.servicio;

import java.util.Collection;

import salud.modelo.PlantillaFormulario;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.formulario.PlantillaFormularioDto;

public interface IServicioFormulariosPlantilla {

	public String altaFormulario(String nombre, String descripcion);
	
	public void modificarFormulario(String id, String nombre, String descripcion) 
			throws EntidadNoEncontrada;
	
	public int agregarPregunta(String id, PreguntaEncuesta tipoDatos) throws EntidadNoEncontrada;
	
	public void eliminarPregunta(String id, int pos) throws EntidadNoEncontrada;
	
	public PlantillaFormulario obtenerFormulario(String id) throws EntidadNoEncontrada;
	
	public Collection<PlantillaFormularioDto> obtenerFormularios();
	
	public void eliminarFormulario(String id) throws EntidadNoEncontrada;
}
