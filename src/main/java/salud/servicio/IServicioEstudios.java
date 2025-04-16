package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioEstudios {

	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada;
	
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada;
	
	public Collection<Estudio> obtenerEstudios();
	
	public Collection<Estudio> obtenerEstudios(Collection<String> ids);
	
	public void eliminarEstudio(String id) throws EntidadNoEncontrada;
}
