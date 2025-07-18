package salud.rest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.auth.JwtUtils;
import salud.modelo.Estudio;
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
				LocalDateTime.parse(estudioDto.getFechaInicio(), DateTimeFormatter.ISO_DATE_TIME), 
				LocalDateTime.parse(estudioDto.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarEstudio(EstudioDto estudioDto, String id) 
			throws Exception {		
		servicioEstudios.modificarEstudio(id, 
				estudioDto.getNombre(), 
				estudioDto.getDescripcion(),
				LocalDateTime.parse(estudioDto.getFechaInicio(), DateTimeFormatter.ISO_DATE_TIME),
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
		Collection<Estudio> estudios = servicioEstudios.obtenerEstudios();
		Collection<EstudioDto> dtos = new LinkedList<EstudioDto>();
		estudios.forEach(a -> dtos.add(EstudioDto.from(a)));
		return ResponseEntity.ok(dtos);
	}
	
	@Override
	public ResponseEntity<Void> eliminarEstudio(String id) throws Exception {
		servicioEstudios.eliminarEstudio(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPacientes(@Valid Collection<String> pacientes, @Valid String id)
			throws Exception {
		String idEspecialista = JwtUtils.getIdUsuario();
		servicioEstudios.agregarPacientes(id, idEspecialista, pacientes);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		String idEspecialista = JwtUtils.getIdUsuario();
		servicioEstudios.agregarSeguimientos(id, idEspecialista, seguimientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		String idEspecialista = JwtUtils.getIdUsuario();
		servicioEstudios.agregarAlertas(id, idEspecialista, alertas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPaciente(String idEstudio, String idPaciente)
			throws Exception {
		servicioEstudios.eliminarPacientes(idEstudio, List.of(idPaciente));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarSeguimiento(String idEstudio, String idSeguimiento)
			throws Exception {
		servicioEstudios.eliminarSeguimientos(idEstudio, List.of(idSeguimiento));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarAlerta(String idEstudio, String idAlerta) throws Exception {
		servicioEstudios.eliminarAlertas(idEstudio, List.of(idAlerta));
		return ResponseEntity.noContent().build();
	}
}
