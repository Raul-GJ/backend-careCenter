package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.consulta.ConsultaDto;
import salud.rest.dto.consulta.RespuestaDto;
import salud.servicio.IServicioConsultas;

@RestController
@RequestMapping("/salud/api/consultas")
public class ControladorConsultas implements ConsultasApi {
	
	// Atributos
	
	private IServicioConsultas servicioConsultas;
	
	// Constructores
	
	public ControladorConsultas(IServicioConsultas servicioConsultas) {
		this.servicioConsultas = servicioConsultas;
	}
	
	// Métodos
	
	public ResponseEntity<ConsultaDto> crearConsulta(ConsultaDto consultaDto) {
		String id = servicioConsultas.altaConsulta(
				consultaDto.getAsunto(),
				consultaDto.getMensaje());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<ConsultaDto> obtenerConsulta(String id) throws Exception {
		ConsultaDto consultaDto = ConsultaDto.from(servicioConsultas.obtenerConsulta(id));
		return ResponseEntity.ok(consultaDto);
	}
	
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultas() throws Exception {
		Collection<ConsultaDto> consultas = servicioConsultas.obtenerConsultas();
		return ResponseEntity.ok(consultas);
	}

	public ResponseEntity<Void> responderConsulta(RespuestaDto respuestaDto, 
			String id) throws Exception {
		servicioConsultas.responderConsulta(id, 
				respuestaDto.getMensaje(),
				LocalDateTime.parse(respuestaDto.getFecha(), DateTimeFormatter.ISO_DATE_TIME));
		
		return ResponseEntity.noContent().build();

	}
}
