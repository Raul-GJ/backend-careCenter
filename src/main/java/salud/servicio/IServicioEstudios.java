package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.estudio.EstudioDto;

public interface IServicioEstudios {

	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin);
	
	public void asignarPacientes(String id, Collection<Paciente> pacientes) throws EntidadNoEncontrada;
	
	public void asignarEspecialistas(String id, Collection<Especialista> especialistas) 
			throws EntidadNoEncontrada;
	
	public void asignarSeguimientos(String id, Collection<Seguimiento> seguimientos) throws EntidadNoEncontrada;
	
	public void asignarAlertas(String id, Collection<Alerta> alertas) throws EntidadNoEncontrada;
	
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada;
	
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada;
	
	public Collection<EstudioDto> obtenerEstudios();
	
	public Page<EstudioDto> obtenerEstudiosPaginado(Pageable pageable);
	
	public void eliminarEstudio(String id) throws EntidadNoEncontrada;
}
