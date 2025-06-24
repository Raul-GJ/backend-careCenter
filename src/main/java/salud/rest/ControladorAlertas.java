package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Alerta;
import salud.rest.dto.alerta.AlertaDto;
import salud.rest.dto.alerta.CrearAlertaDto;
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
	
	@Override
	public ResponseEntity<AlertaDto> crearAlerta(CrearAlertaDto alertaDto) throws Exception {
		String id = servicioAlertas.altaAlerta(
				alertaDto.getEmisor(),
				alertaDto.getReceptor(),
				false,
				alertaDto.getAsunto(),
				alertaDto.getMensaje(),
				LocalDateTime.parse(alertaDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<AlertaDto> obtenerAlerta(String id) throws Exception {
		AlertaDto alertaDto = AlertaDto.from(servicioAlertas.obtenerAlerta(id));
		return ResponseEntity.ok(alertaDto);
	}
	
	@Override
	public ResponseEntity<Collection<AlertaDto>> obtenerAlertas() throws Exception {
		Collection<Alerta> alertas = servicioAlertas.obtenerAlertas();
		Collection<AlertaDto> dtos = new LinkedList<AlertaDto>();
		alertas.forEach(a -> dtos.add(AlertaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarAlerta(String id) throws Exception {
		servicioAlertas.eliminarAlerta(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Collection<AlertaDto>> obtenerAlertasUsuario(String id) throws Exception {
		Collection<Alerta> alertas = servicioAlertas.obtenerAlertasUsuario(id);
		Collection<AlertaDto> dtos = new LinkedList<AlertaDto>();
		alertas.forEach(a -> dtos.add(AlertaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> leerAlerta(String id) throws Exception {
		servicioAlertas.leerAlerta(id);
		return ResponseEntity.noContent().build();
	}
}
