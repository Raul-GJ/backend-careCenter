package salud.servicio;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Plantilla;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioEspecialistas {

	// Especialistas
	
	public String altaEspecialista(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String especialidad);
	
	public void modificarEspecialista(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String nCol, String especialidad) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;

	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void agregarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada;
	
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void eliminarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<Especialista> obtenerEspecialistas();
	
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids);
	
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada;
	
}
