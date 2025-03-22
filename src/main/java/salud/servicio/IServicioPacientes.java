package salud.servicio;

import java.util.Collection;

import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.PacienteDto;

public interface IServicioPacientes {
	
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, MedicoFamilia medicoCabecera);
	
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, MedicoFamilia medicoCabecera) throws EntidadNoEncontrada;
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<PacienteDto> obtenerPacientes() throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id) throws EntidadNoEncontrada;
}
