package salud.servicio;

import java.util.Collection;
import java.util.List;

import salud.modelo.Formulario;
import salud.modelo.MedicoFamilia;
import salud.modelo.Paciente;
import salud.modelo.PlantillaFormulario;
import salud.modelo.encuesta.DatoEncuesta;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.formulario.FormularioDto;
import salud.rest.dto.usuario.PacienteDto;

public interface IServicioPacientes {

	// Pacientes
	
	public String altaPaciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, MedicoFamilia medicoCabecera);
	
	public void modificarPaciente(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, MedicoFamilia medicoCabecera) throws EntidadNoEncontrada;
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<PacienteDto> obtenerPacientes() throws EntidadNoEncontrada;
	
	public void eliminarPaciente(String id) throws EntidadNoEncontrada;
	
	// Formularios
	
	public String altaFormulario(String idUsr, PlantillaFormulario formularioPlantilla, 
			Collection<DatoEncuesta> datos) throws EntidadNoEncontrada;
	
	public void modificarFormulario(String idUsr, int pos, PlantillaFormulario formularioPlantilla, 
			Collection<DatoEncuesta> datos) throws EntidadNoEncontrada;
	
	public Formulario obtenerFormulario(String idUsr, int pos) throws EntidadNoEncontrada;
	
	public List<FormularioDto> obtenerFormularios(String idUsr) throws EntidadNoEncontrada;
	
	public void eliminarFormulario(String idUsr, int pos) throws EntidadNoEncontrada;
}
