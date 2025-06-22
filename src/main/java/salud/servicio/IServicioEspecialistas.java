package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;

import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Plantilla;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioEspecialistas {

	// Especialistas
	
	public String altaEspecialista(String nombre, String apellidos, String email, 
			String telefono, LocalDate fechaNacimiento, String sexo, String direccion, String dni, 
			String contrasenya, String nCol, String centroDeSalud, String especialidad);
	
	public void modificarEspecialista(String id, String nombre, String apellidos, 
			String email, String telefono, LocalDate fechaNacimiento, String sexo, String direccion, 
			String dni, String nCol, String centroDeSalud, String especialidad) 
					throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;

	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void agregarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada;
	
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void eliminarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada;
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<Especialista> obtenerEspecialistas();
	
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids);
	
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada;
	
}
