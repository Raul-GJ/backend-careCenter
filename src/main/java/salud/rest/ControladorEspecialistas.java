package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Especialista;
import salud.rest.dto.usuario.EspecialistaDto;
import salud.servicio.IServicioEspecialistas;

@RestController
@RequestMapping("/salud/api/usuarios/especialistas")
public class ControladorEspecialistas implements EspecialistasApi {

	// Atributos
	
	private IServicioEspecialistas servicioEspecialistas;
	
	// Constructores
	
	public ControladorEspecialistas(IServicioEspecialistas servicioEspecialistas) {
		super();
		this.servicioEspecialistas = servicioEspecialistas;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<EspecialistaDto> altaEspecialista(@Valid EspecialistaDto especialistaDto) {
		String id = servicioEspecialistas.altaEspecialista(especialistaDto.getNombre(), 
				especialistaDto.getApellido1(), 
				especialistaDto.getApellido2(), 
				especialistaDto.getEmail(), 
				especialistaDto.getTelefono(), 
				especialistaDto.getnCol(),
				especialistaDto.getEspecialidad());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarEspecialista(@Valid EspecialistaDto especialistaDto, @Valid String id)
			throws Exception {
		servicioEspecialistas.modificarEspecialista(id,
				especialistaDto.getNombre(), 
				especialistaDto.getApellido1(), 
				especialistaDto.getApellido2(), 
				especialistaDto.getEmail(), 
				especialistaDto.getTelefono(), 
				especialistaDto.getnCol(),
				especialistaDto.getEspecialidad());
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<EspecialistaDto> obtenerEspecialista(@Valid String id) throws Exception {
		EspecialistaDto dto = EspecialistaDto.from(servicioEspecialistas.obtenerEspecialista(id));
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<Collection<EspecialistaDto>> obtenerEspecialistas() throws Exception {
		Collection<Especialista> especialistas = servicioEspecialistas.obtenerEspecialistas();
		Collection<EspecialistaDto> dtos = new LinkedList<EspecialistaDto>();
		especialistas.forEach(a -> dtos.add(EspecialistaDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarEspecialista(@Valid String id) throws Exception {
		servicioEspecialistas.eliminarEspecialista(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPacientes(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarPacientes(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPacientes(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.eliminarPacientes(id, ids);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> agregarEstudiosCreador(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarEstudiosCreador(id, ids);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> agregarEstudiosEditor(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarEstudiosEditor(id, ids);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> agregarEstudiosObservador(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarEstudiosObservador(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarEstudios(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.eliminarEstudios(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarPlantillas(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.eliminarPlantillas(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarConsultas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarConsultas(id, ids);
		return ResponseEntity.noContent().build();
	}
}
