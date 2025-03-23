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

import salud.rest.dto.estudio.CrearEstudioDto;
import salud.rest.dto.estudio.EstudioDto;
import salud.servicio.IServicioEstudios;

@RestController
@RequestMapping("/salud/api/estudios")
public class ControladorEstudios implements EstudiosApi {
	
	// Atributos
	
	private IServicioEstudios servicioEstudios;
	
	// Constructores
	
	public ControladorEstudios(IServicioEstudios servicioEstudios) {
		super();
		this.servicioEstudios = servicioEstudios;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<EstudioDto> crearEstudio(CrearEstudioDto estudioDto) throws Exception {
		String id = servicioEstudios.altaEstudio(estudioDto.getNombre(), 
				estudioDto.getDescripcion(),
				LocalDateTime.parse(estudioDto.getFechaAlta(), DateTimeFormatter.ISO_DATE_TIME), 
				LocalDateTime.parse(estudioDto.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME),
				estudioDto.getCreador());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarEstudio(CrearEstudioDto estudioDto, String id) 
			throws Exception {		
		servicioEstudios.modificarEstudio(id, 
				estudioDto.getNombre(), 
				estudioDto.getDescripcion(), 
				LocalDateTime.parse(estudioDto.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME));
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<EstudioDto> obtenerEstudio(String id) throws Exception {
		EstudioDto estudioDto = EstudioDto.from(servicioEstudios.obtenerEstudio(id));
		return ResponseEntity.ok(estudioDto);
	}
	
	@Override
	public ResponseEntity<Collection<EstudioDto>> obtenerEstudios() throws Exception {
		Collection<EstudioDto> estudios = servicioEstudios.obtenerEstudios();
		return ResponseEntity.ok(estudios);
	}
	
	@Override
	public ResponseEntity<Void> eliminarEstudio(String id) throws Exception {
		servicioEstudios.eliminarEstudio(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarPacientes(@Valid Collection<String> pacientes, @Valid String id)
			throws Exception {
		servicioEstudios.asignarPacientes(id, pacientes);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarEspecialistas(@Valid Collection<String> especialistas, @Valid String id)
			throws Exception {
		servicioEstudios.asignarEspecialistas(id, especialistas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		servicioEstudios.asignarSeguimientos(id, seguimientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		servicioEstudios.asignarAlertas(id, alertas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPacientes(@Valid Collection<String> pacientes, @Valid String id)
			throws Exception {
		servicioEstudios.eliminarPacientes(id, pacientes);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarEspecialistas(@Valid Collection<String> especialistas, @Valid String id)
			throws Exception {
		servicioEstudios.eliminarEspecialistas(id, especialistas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		servicioEstudios.eliminarSeguimientos(id, seguimientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		servicioEstudios.eliminarAlertas(id, alertas);
		return ResponseEntity.noContent().build();
	}
}
