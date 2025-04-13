package salud.servicio;

import java.util.Collection;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.PlantillaFormulario;
import salud.modelo.RolEstudio;
import salud.repositorio.EntidadNoEncontrada;

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
	
	public void agregarEstudiosCreador(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void agregarEstudiosEditor(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void agregarEstudiosObservador(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void agregarEstudio(String id, Estudio estudio, RolEstudio rol) throws EntidadNoEncontrada;
	
	public void eliminarEstudios(String id, Collection<String> estudios) throws EntidadNoEncontrada;
	
	public void eliminarEstudio(String id, Estudio estudio) throws EntidadNoEncontrada;

	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void agregarPlantilla(String id, PlantillaFormulario plantilla) throws EntidadNoEncontrada;
	
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada;
	
	public void eliminarPlantilla(String id, PlantillaFormulario plantilla) throws EntidadNoEncontrada;
	
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada;
	
	public void agregarConsulta(String id, Consulta consulta) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada;
	
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada;
	
	public Collection<Especialista> obtenerEspecialistas();
	
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids);
	
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada;
	
}
