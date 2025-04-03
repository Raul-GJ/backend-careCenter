package salud.servicio;

import java.util.Collection;

import salud.modelo.Especialista;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.EspecialistaDto;

public interface IServicioEspecialistas {

	// Especialistas
	
	public String altaEspecialista(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String especialidad);
	
	public void modificarEspecialista(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String nCol, String especialidad) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarEstudiosCreador(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void agregarEstudiosEditor(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void agregarEstudiosObservador(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void eliminarEstudios(String id, Collection<String> estudios) throws EntidadNoEncontrada;

	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada;
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<EspecialistaDto> obtenerEspecialistas();
	
	public Collection<EspecialistaDto> obtenerEspecialistas(Collection<String> ids);
	
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada;
	
}
