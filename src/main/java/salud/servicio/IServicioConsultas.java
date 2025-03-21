package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Consulta;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.consulta.ConsultaDto;

public interface IServicioConsultas {

	public String altaConsulta(String asunto, String mensaje);
	
	public void modificarConsulta(String id, String asunto, String mensaje) 
			throws EntidadNoEncontrada;
	
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada;
	
	public Collection<ConsultaDto> obtenerConsultas();
	
	public Page<ConsultaDto> obtenerConsultasPaginado(Pageable pageable);
	
	public void eliminarConsulta(String id) throws EntidadNoEncontrada;
	
	public void responderConsulta(String id, String mensaje, LocalDateTime fecha) throws EntidadNoEncontrada;
}
