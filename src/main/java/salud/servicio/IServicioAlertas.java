package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.Alerta;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.alerta.AlertaDto;

public interface IServicioAlertas {

	public String altaAlerta(String asunto, String mensaje, LocalDateTime fecha);
	
	public void modificarAlerta(String id, String asunto, String mensaje, LocalDateTime fecha) 
			throws EntidadNoEncontrada;
	
	public Alerta obtenerAlerta(String id) throws EntidadNoEncontrada;
	
	public Collection<AlertaDto> obtenerAlertas();
	
	public Collection<AlertaDto> obtenerAlertas(Collection<String> ids);
	
	public Page<AlertaDto> obtenerAlertasPaginado(Pageable pageable);
	
	public void eliminarAlerta(String id) throws EntidadNoEncontrada;
}
