package salud.servicio;

import java.util.Collection;

import salud.modelo.MedicoFamilia;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.usuario.MedicoDto;

public interface IServicioMedicos {

	// Medicos de familia
	
	public String altaMedico(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal);
	
	public void modificarMedico(String id, String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal) throws EntidadNoEncontrada;
	
	public MedicoFamilia obtenerMedico(String id) throws EntidadNoEncontrada;
	
	public Collection<MedicoDto> obtenerMedicos() throws EntidadNoEncontrada;
	
	public void eliminarMedico(String id) throws EntidadNoEncontrada;
	
}
