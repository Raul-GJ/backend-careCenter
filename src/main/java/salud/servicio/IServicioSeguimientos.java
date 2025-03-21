package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import salud.modelo.PlantillaFormulario;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.rest.dto.seguimiento.SeguimientoDto;

public interface IServicioSeguimientos {

	public String altaSeguimiento(LocalDateTime fecha, LocalDateTime tiempo,  PlantillaFormulario formulario);
	
	public void modificarSeguimiento(String id, LocalDateTime fecha, LocalDateTime tiempo,
			PlantillaFormulario formulario) throws EntidadNoEncontrada;
	
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada;
	
	public Collection<SeguimientoDto> obtenerSeguimientos();
	
	public Page<SeguimientoDto> obtenerSeguimientosPaginado(Pageable pageable);
	
	public void eliminarSeguimiento(String id) throws EntidadNoEncontrada;
}
