package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.alerta.AlertaDto;
import salud.servicio.IServicioAlertas;

@RestController
@RequestMapping("/salud/api/alertas")
public class ControladorAlertas implements AlertasApi {
	
	// Atributos
	
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ControladorAlertas(IServicioAlertas servicioAlertas) {
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos
	
	public ResponseEntity<AlertaDto> crearAlerta(AlertaDto alertaDto) {
		String id = servicioAlertas.altaAlerta(
				alertaDto.getAsunto(),
				alertaDto.getMensaje(),
				LocalDateTime.parse(alertaDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Void> modificarAlerta(AlertaDto alertaDto, String id) 
			throws Exception {
		servicioAlertas.modificarAlerta(id, 
				alertaDto.getAsunto(),
				alertaDto.getMensaje(),
				LocalDateTime.parse(alertaDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME));
		
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<AlertaDto> obtenerAlerta(String id) throws Exception {
		AlertaDto alertaDto = AlertaDto.from(servicioAlertas.obtenerAlerta(id));
		return ResponseEntity.ok(alertaDto);
	}
	
	public ResponseEntity<Collection<AlertaDto>> obtenerAlertas() throws Exception {
		Collection<AlertaDto> alertas = servicioAlertas.obtenerAlertas();
		return ResponseEntity.ok(alertas);
	}

	public ResponseEntity<Void> eliminarAlerta(String id) throws Exception {
		servicioAlertas.eliminarAlerta(id);
		return ResponseEntity.noContent().build();
	}
}
