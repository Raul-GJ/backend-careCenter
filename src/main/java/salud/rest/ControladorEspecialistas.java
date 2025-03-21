package salud.rest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
				especialistaDto.getApellido1(), 
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
				especialistaDto.getApellido1(), 
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
		Collection<EspecialistaDto> especialistas = servicioEspecialistas.obtenerEspecialistas();
		return ResponseEntity.ok(especialistas);
	}

	@Override
	public ResponseEntity<Void> eliminarEspecialista(@Valid String id) throws Exception {
		servicioEspecialistas.eliminarEspecialista(id);
		return ResponseEntity.noContent().build();
	}

}
