package salud.rest;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Void> modificarEspecialista(@Valid EspecialistaDto especialistaDto, @Valid String id)
			throws Exception {
		servicioEspecialistas.modificarEspecialista(id,
				especialistaDto.getNombre(), 
				especialistaDto.getApellidos(), 
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
	public ResponseEntity<Void> agregarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarPlantillas(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.eliminarPlantillas(id, ids);
		return ResponseEntity.noContent().build();
	}
}
