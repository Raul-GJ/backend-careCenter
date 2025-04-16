package salud.servicio;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioPacientes {
	
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String medicoCabecera) throws EntidadNoEncontrada;
	
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String medicoCabecera) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void agregarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada;
	
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void agregarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada;
	
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;

	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;

	public void eliminarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada;
	
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<Paciente> obtenerPacientes(Collection<String> ids);
	
	public Collection<Paciente> obtenerPacientes();
	
	public void eliminarPaciente(String id) throws EntidadNoEncontrada;
}
