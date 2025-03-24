package salud.rest;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.consulta.ConsultaDto;
import salud.rest.dto.consulta.CrearRespuestaDto;
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
	
	@Override
	public ResponseEntity<ConsultaDto> crearConsulta(ConsultaDto consultaDto) {
		String id = servicioConsultas.altaConsulta(
				consultaDto.getAsunto(),
				consultaDto.getMensaje());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<ConsultaDto> obtenerConsulta(String id) throws Exception {
		ConsultaDto consultaDto = ConsultaDto.from(servicioConsultas.obtenerConsulta(id));
		return ResponseEntity.ok(consultaDto);
	}
	
	@Override
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultas(
			Collection<String> ids) throws Exception {
		if (ids == null || ids.isEmpty()) {
			Collection<ConsultaDto> consultas = servicioConsultas.obtenerConsultas();
			return ResponseEntity.ok(consultas);
		}
		Collection<ConsultaDto> consultas = servicioConsultas.obtenerConsultas(ids);
		return ResponseEntity.ok(consultas);
	}
	
	@Override
	public ResponseEntity<Void> responderConsulta(CrearRespuestaDto respuestaDto, 
			String id) throws Exception {
		servicioConsultas.responderConsulta(id, 
				respuestaDto.getMensaje());
		
		return ResponseEntity.noContent().build();

	}
}
