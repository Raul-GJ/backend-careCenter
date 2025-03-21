package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.seguimiento.SeguimientoDto;
import salud.servicio.IServicioFormulariosPlantilla;
import salud.servicio.IServicioSeguimientos;

@RestController
@RequestMapping("/salud/api/seguimientos")
public class ControladorSeguimientos implements SeguimientosApi {
	
	// Atributos
	
	private IServicioSeguimientos servicioSeguimientos;
	private IServicioFormulariosPlantilla servicioFormulariosPlantilla;
	
	// Constructores
	
	public ControladorSeguimientos(IServicioSeguimientos servicioSeguimientos,
			IServicioFormulariosPlantilla servicioFormulariosPlantilla) {
		super();
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioFormulariosPlantilla = servicioFormulariosPlantilla;
	}
	
	// Métodos
	
	public ResponseEntity<SeguimientoDto> crearSeguimiento(SeguimientoDto seguimientoDto) 
			throws Exception {
		String id = servicioSeguimientos.altaSeguimiento(
				LocalDateTime.parse(seguimientoDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME),
				LocalDateTime.parse(seguimientoDto.getPlazo(), DateTimeFormatter.ISO_DATE_TIME),
				servicioFormulariosPlantilla.obtenerFormulario(seguimientoDto.getFormulario()));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Void> modificarSeguimiento(SeguimientoDto seguimientoDto, 
			String id) throws Exception {
		servicioSeguimientos.modificarSeguimiento(id,
				LocalDateTime.parse(seguimientoDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME),
				LocalDateTime.parse(seguimientoDto.getPlazo(), DateTimeFormatter.ISO_DATE_TIME),
				servicioFormulariosPlantilla.obtenerFormulario(seguimientoDto.getFormulario()));
		
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<SeguimientoDto> obtenerSeguimiento(String id) throws Exception {
		SeguimientoDto seguimientoDto = SeguimientoDto.from(servicioSeguimientos.obtenerSeguimiento(id));
		return ResponseEntity.ok(seguimientoDto);
	}
	
	public ResponseEntity<Collection<SeguimientoDto>> obtenerSeguimientos() throws Exception {
		Collection<SeguimientoDto> seguimientos = servicioSeguimientos.obtenerSeguimientos();
		return ResponseEntity.ok(seguimientos);
	}

	public ResponseEntity<Void> eliminarSeguimiento(String id) throws Exception {
		servicioSeguimientos.eliminarSeguimiento(id);
		return ResponseEntity.noContent().build();
	}
}
