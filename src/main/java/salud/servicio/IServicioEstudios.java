package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import salud.modelo.Estudio;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioEstudios {

	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaInicio, 
			LocalDateTime fechaFin) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, String idEspecialista, Collection<String> pacientes) 
			throws EntidadNoEncontrada;
	
	public void agregarSeguimientos(String id, String idEspecialista, Collection<String> seguimientos) 
			throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, String idEspecialista, Collection<String> alertas) 
			throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) throws EntidadNoEncontrada;
	
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada;
	
	public Collection<Estudio> obtenerEstudios();
	
	public Collection<Estudio> obtenerEstudios(Collection<String> ids);
	
	public void eliminarEstudio(String id) throws EntidadNoEncontrada;
}
