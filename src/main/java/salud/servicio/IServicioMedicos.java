package salud.servicio;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioMedicos {

	// Medicos de familia
	
	public String altaMedico(String nombre, String apellidos, String email, 
			String telefono, String contrasenya, String nCol);
	
	public void modificarMedico(String id, String nombre, String apellidos, String email, 
			String telefono, String nCol) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada;
	
	public Collection<Medico> obtenerMedicos();
	
	public Collection<Medico> obtenerMedicos(Collection<String> ids);
	
	public void eliminarMedico(String id) throws EntidadNoEncontrada;
	
}
