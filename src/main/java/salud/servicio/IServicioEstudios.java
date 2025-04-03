package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Estudio;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.estudio.EstudioDto;

public interface IServicioEstudios {

	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin, String creador) throws EntidadNoEncontrada;
	
	public void asignarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void asignarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void asignarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada;
	
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada;
	
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada;
	
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada;
	
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada;
	
	public Collection<EstudioDto> obtenerEstudios();
	
	public Collection<EstudioDto> obtenerEstudios(Collection<String> ids);
	
	public Page<EstudioDto> obtenerEstudiosPaginado(Pageable pageable);
	
	public void eliminarEstudio(String id) throws EntidadNoEncontrada;
}
