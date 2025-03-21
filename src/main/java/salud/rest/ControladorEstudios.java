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
import salud.modelo.Seguimiento;
import salud.rest.dto.estudio.EstudioDto;
import salud.servicio.IServicioAlertas;
import salud.servicio.IServicioEstudios;
import salud.servicio.IServicioSeguimientos;

@RestController
@RequestMapping("/salud/api/estudios")
public class ControladorEstudios implements EstudiosApi {
	
	// Atributos
	
	private IServicioEstudios servicioEstudios;
	private IServicioAlertas servicioAlertas;
	private IServicioSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ControladorEstudios(IServicioEstudios servicioEstudios, IServicioAlertas servicioAlertas,
			IServicioSeguimientos servicioSeguimientos) {
		super();
		this.servicioEstudios = servicioEstudios;
		this.servicioAlertas = servicioAlertas;
		this.servicioSeguimientos = servicioSeguimientos;
	}
	
	// Métodos
	
	public ResponseEntity<EstudioDto> crearEstudio(EstudioDto estudioDto) throws Exception {
		Collection<String> seguimientosString = estudioDto.getSeguimientos();
		Collection<String> alertasString = estudioDto.getAlertas();
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		Collection<Alerta> alertas = new LinkedList<Alerta>();
		for (String seguimiento : seguimientosString) {
			seguimientos.add(servicioSeguimientos.obtenerSeguimiento(seguimiento));
		}
		for (String alerta : alertasString) {
			alertas.add(servicioAlertas.obtenerAlerta(alerta));
		}
		
		String id = servicioEstudios.altaEstudio(estudioDto.getNombre(), 
				estudioDto.getDescripcion(),
				LocalDateTime.parse(estudioDto.getFechaAlta(), DateTimeFormatter.ISO_DATE_TIME), 
				LocalDateTime.parse(estudioDto.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Void> modificarEstudio(EstudioDto estudioDto, String id) 
			throws Exception {
		Collection<String> seguimientosString = estudioDto.getSeguimientos();
		Collection<String> alertasString = estudioDto.getAlertas();
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		Collection<Alerta> alertas = new LinkedList<Alerta>();
		for (String seguimiento : seguimientosString) {
			seguimientos.add(servicioSeguimientos.obtenerSeguimiento(seguimiento));
		}
		for (String alerta : alertasString) {
			alertas.add(servicioAlertas.obtenerAlerta(alerta));
		}
		
		servicioEstudios.modificarEstudio(id, 
				estudioDto.getNombre(), 
				estudioDto.getDescripcion(), 
				LocalDateTime.parse(estudioDto.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME));
		
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<EstudioDto> obtenerEstudio(String id) throws Exception {
		EstudioDto estudioDto = EstudioDto.from(servicioEstudios.obtenerEstudio(id));
		return ResponseEntity.ok(estudioDto);
	}
	
	public ResponseEntity<Collection<EstudioDto>> obtenerEstudios() throws Exception {
		Collection<EstudioDto> estudios = servicioEstudios.obtenerEstudios();
		return ResponseEntity.ok(estudios);
	}
	
	public ResponseEntity<Void> eliminarEstudio(String id) throws Exception {
		servicioEstudios.eliminarEstudio(id);
		return ResponseEntity.noContent().build();
	}
}
