package salud.servicio;

import java.util.Collection;

import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.PacienteDto;

public interface IServicioPacientes {
	
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String medicoCabecera) throws EntidadNoEncontrada;
	
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String medicoCabecera) throws EntidadNoEncontrada;
	
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;

	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada;
	
	public void agregarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada;
	
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;

	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<PacienteDto> obtenerPacientes(Collection<String> ids);
	
	public Collection<PacienteDto> obtenerPacientes();
	
	public void eliminarPaciente(String id) throws EntidadNoEncontrada;
}
