package salud.servicio;

import java.util.Collection;

import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.repositorio.EntidadNoEncontrada;

public interface IServicioMedicos {

	// Medicos de familia
	
	public String altaMedico(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal);
	
	public void modificarMedico(String id, String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal) throws EntidadNoEncontrada;
	
	public void agregarPacientes(String id, Collection<String> ids) throws EntidadNoEncontrada;
	
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> ids) throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada;
	
	public MedicoFamilia obtenerMedico(String id) throws EntidadNoEncontrada;
	
	public Collection<MedicoFamilia> obtenerMedicos();
	
	public Collection<MedicoFamilia> obtenerMedicos(Collection<String> ids);
	
	public void eliminarMedico(String id) throws EntidadNoEncontrada;
	
}
