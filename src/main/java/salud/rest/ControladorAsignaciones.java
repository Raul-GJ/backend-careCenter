package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.asignacion.AsignacionEstudioDto;
import salud.rest.dto.asignacion.CrearAsignacionEstudioDto;
import salud.rest.dto.asignacion.ModificarAsignacionDto;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.IservicioAsignacionEstudio;

@RestController
@RequestMapping("/salud/api/asignaciones")
public class ControladorAsignaciones implements AsignacionesApi {

	// Atributos
	
	private IservicioAsignacionEstudio servicioAsignaciones;
	
	// Constructores
	
	public ControladorAsignaciones(IservicioAsignacionEstudio servicioAsignaciones) {
		super();
		this.servicioAsignaciones = servicioAsignaciones;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<Void> agregarAsignacion(CrearAsignacionEstudioDto asignacionDto) 
			throws EntidadNoEncontrada {
		String id = servicioAsignaciones.agregarAsignacion(
				asignacionDto.getEspecialista(),
				asignacionDto.getEstudio(),
				asignacionDto.getRol());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> modificarAsignacion(String id, ModificarAsignacionDto asignacionDto) 
			throws EntidadNoEncontrada {
		servicioAsignaciones.modificarAsignacion(id, asignacionDto.getRol());
		return null;
	}

	@Override
	public ResponseEntity<Void> eliminarAsignacion(String id) throws EntidadNoEncontrada {
		servicioAsignaciones.eliminarAsignacion(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<AsignacionEstudioDto> obtenerAsignacion(String id) throws EntidadNoEncontrada {
		AsignacionEstudioDto dto = AsignacionEstudioDto.from(servicioAsignaciones.obtenerAsignacion(id));
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<Collection<AsignacionEstudioDto>> obtenerEspecialistas(String id) throws EntidadNoEncontrada {
		Collection<AsignacionEstudioDto> dtos = new LinkedList<AsignacionEstudioDto>();
		servicioAsignaciones.obtenerEspecialistas(id).forEach(ae -> dtos.add(AsignacionEstudioDto.from(ae)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Collection<AsignacionEstudioDto>> obtenerEstudios(String id) throws EntidadNoEncontrada {
		Collection<AsignacionEstudioDto> dtos = new LinkedList<AsignacionEstudioDto>();
		servicioAsignaciones.obtenerEstudios(id).forEach(ae -> dtos.add(AsignacionEstudioDto.from(ae)));
		return ResponseEntity.ok(dtos);
	}
}
