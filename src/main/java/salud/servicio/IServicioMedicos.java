package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;

import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioMedicos {

	// Medicos de familia
	
	public String altaMedico(String nombre, String apellidos, String email, String telefono, 
			LocalDate fechaNacimiento, String sexo, String direccion, String dni,
			String contrasenya, String nCol, String centroDeSalud);
	
	public void modificarMedico(String id, String nombre, String apellidos, String email, 
			String telefono, LocalDate fechaNacimiento, String sexo, String direccion, String dni, 
			String nCol, String centroDeSalud) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada;
	
	public Collection<Medico> obtenerMedicos();
	
	public Collection<Medico> obtenerMedicos(Collection<String> ids);
	
	public void eliminarMedico(String id) throws EntidadNoEncontrada;
	
}
