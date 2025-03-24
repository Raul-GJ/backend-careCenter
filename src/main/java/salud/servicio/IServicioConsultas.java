package salud.servicio;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Consulta;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.consulta.ConsultaDto;

public interface IServicioConsultas {

	public String altaConsulta(String asunto, String mensaje);
	
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada;
	
	public Collection<ConsultaDto> obtenerConsultas();
	
	public Collection<ConsultaDto> obtenerConsultas(Collection<String> ids);
	
	public Page<ConsultaDto> obtenerConsultasPaginado(Pageable pageable);
	
	public void eliminarConsulta(String id) throws EntidadNoEncontrada;
	
	public void responderConsulta(String id, String mensaje) throws EntidadNoEncontrada;
}
