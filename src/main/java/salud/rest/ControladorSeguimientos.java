package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.formulario.RellenarFormularioDto;
import salud.rest.dto.seguimiento.CrearSeguimientoDto;
import salud.rest.dto.seguimiento.SeguimientoDto;
import salud.servicio.IServicioSeguimientos;

@RestController
@RequestMapping("/salud/api/seguimientos")
public class ControladorSeguimientos implements SeguimientosApi {
	
	// Atributos
	
	private IServicioSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ControladorSeguimientos(IServicioSeguimientos servicioSeguimientos) {
		super();
		this.servicioSeguimientos = servicioSeguimientos;
	}
	
	// Métodos
	
	// Seguimientos
	
	@Override
	public ResponseEntity<SeguimientoDto> crearSeguimiento(CrearSeguimientoDto seguimientoDto) 
			throws Exception {
		String id = servicioSeguimientos.altaSeguimiento(
				LocalDateTime.parse(seguimientoDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME),
				LocalDateTime.parse(seguimientoDto.getPlazo(), DateTimeFormatter.ISO_DATE_TIME),
				seguimientoDto.getPlantilla());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> modificarSeguimiento(CrearSeguimientoDto seguimientoDto, 
			String id) throws Exception {
		servicioSeguimientos.modificarSeguimiento(id,
				LocalDateTime.parse(seguimientoDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME),
				LocalDateTime.parse(seguimientoDto.getPlazo(), DateTimeFormatter.ISO_DATE_TIME),
				seguimientoDto.getPlantilla());
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<SeguimientoDto> obtenerSeguimiento(String id) throws Exception {
		SeguimientoDto seguimientoDto = SeguimientoDto.from(servicioSeguimientos.obtenerSeguimiento(id));
		return ResponseEntity.ok(seguimientoDto);
	}
	
	@Override
	public ResponseEntity<Collection<SeguimientoDto>> obtenerSeguimientos() throws Exception {
		Collection<SeguimientoDto> seguimientos = servicioSeguimientos.obtenerSeguimientos();
		return ResponseEntity.ok(seguimientos);
	}
	
	@Override
	public ResponseEntity<Void> eliminarSeguimiento(String id) throws Exception {
		servicioSeguimientos.eliminarSeguimiento(id);
		return ResponseEntity.noContent().build();
	}
	
	// Formularios

	@Override
	public ResponseEntity<Void> rellenarFormulario(@Valid String id, 
			@Valid RellenarFormularioDto formularioDto)
			throws Exception {
		servicioSeguimientos.rellenarFormulario(id, formularioDto.getRespuestas());
		return ResponseEntity.noContent().build();
	}

	
}
