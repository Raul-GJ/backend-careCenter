package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;

import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioPacientes {
	
	public String altaPaciente(String nombre, String apellidos, String email, 
			String telefono, LocalDate fechaNacimiento, String sexo, String direccion, String dni,
			String nss, String contrasenya) throws EntidadNoEncontrada;
	
	public void modificarPaciente(String id, String nombre, String apellidos, 
			String email, String telefono, LocalDate fechaNacimiento, String sexo, String direccion, 
			String dni, String nss) throws EntidadNoEncontrada;
	
	public void establecerMedico(String id, Medico Medico) throws EntidadNoEncontrada;
	
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void agregarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada;
	
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;

	public void eliminarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada;
	
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientosGrupo(String id, String idGrupo) throws EntidadNoEncontrada;
	
	public void agregarAlergias(String id, Collection<String> alergias) throws EntidadNoEncontrada;
	
	public void eliminarAlergia(String id, String alergia) throws EntidadNoEncontrada;
	
	public void agregarTratamientos(String id, Collection<String> tratamientos) throws EntidadNoEncontrada;
	
	public void eliminarTratamiento(String id, String tratamiento) throws EntidadNoEncontrada;
	
	public void agregarNotas(String id, Collection<String> notas) throws EntidadNoEncontrada;
	
	public void eliminarNota(String id, String idNota) throws EntidadNoEncontrada;
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<Paciente> obtenerPacientes(Collection<String> ids);
	
	public Collection<Paciente> obtenerPacientes();
	
	public void eliminarPaciente(String id) throws EntidadNoEncontrada;
}
