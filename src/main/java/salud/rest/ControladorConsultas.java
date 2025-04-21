package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Consulta;
import salud.rest.dto.consulta.ConsultaDto;
import salud.rest.dto.consulta.CrearConsultaDto;
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
	public ResponseEntity<ConsultaDto> crearConsultaMedico(CrearConsultaDto consultaDto) throws Exception {
		String id = servicioConsultas.altaConsultaMedico(
				consultaDto.getAsunto(),
				consultaDto.getMensaje(),
				consultaDto.getEmisor(),
				consultaDto.getReceptor());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<ConsultaDto> crearConsultaEspecialista(CrearConsultaDto consultaDto) 
			throws Exception {
		String id = servicioConsultas.altaConsultaEspecialista(
				consultaDto.getAsunto(),
				consultaDto.getMensaje(),
				consultaDto.getEmisor(),
				consultaDto.getReceptor());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<ConsultaDto> obtenerConsulta(String id) throws Exception {
		ConsultaDto consultaDto = ConsultaDto.from(servicioConsultas.obtenerConsulta(id));
		return ResponseEntity.ok(consultaDto);
	}
	
	@Override
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultas() throws Exception {
		Collection<Consulta> consultas = servicioConsultas.obtenerConsultas();
		Collection<ConsultaDto> dtos = new LinkedList<ConsultaDto>();
		consultas.forEach(a -> dtos.add(ConsultaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}
	

	@Override
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultasPaciente(String id) throws Exception {
		Collection<Consulta> consultas = servicioConsultas.obtenerConsultasPaciente(id);
		Collection<ConsultaDto> dtos = new LinkedList<ConsultaDto>();
		consultas.forEach(a -> dtos.add(ConsultaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Collection<ConsultaDto>> obtenerConsultasSanitario(String id) throws Exception {
		Collection<Consulta> consultas = servicioConsultas.obtenerConsultasSanitario(id);
		Collection<ConsultaDto> dtos = new LinkedList<ConsultaDto>();
		consultas.forEach(a -> dtos.add(ConsultaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}
	
	@Override
	public ResponseEntity<Void> responderConsulta(CrearRespuestaDto respuestaDto, 
			String id) throws Exception {
		servicioConsultas.responderConsulta(id, 
				respuestaDto.getMensaje());
		
		return ResponseEntity.noContent().build();

	}
}
