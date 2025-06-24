package salud.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import salud.auth.JwtUtils;
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
		if (JwtUtils.getIdUsuario().equals(id)) {
			servicioEspecialistas.modificarEspecialista(id,
				especialistaDto.getNombre(), 
				especialistaDto.getApellidos(), 
				especialistaDto.getEmail(), 
				especialistaDto.getTelefono(),
				LocalDate.parse(especialistaDto.getFechaNacimiento(), DateTimeFormatter.ISO_DATE),
				especialistaDto.getSexo(),
				especialistaDto.getDireccion(),
				especialistaDto.getDni(),
				especialistaDto.getnCol(),
				especialistaDto.getCentroDeSalud(),
				especialistaDto.getEspecialidad());
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<EspecialistaDto> obtenerEspecialista(@Valid String id) throws Exception {
		Especialista especialista = servicioEspecialistas.obtenerEspecialista(id);
		EspecialistaDto especialistaDto;
		
		if (JwtUtils.getIdUsuario().equals(id)) {
			// Solo el porpio especialista tiene acceso a toda su información
			especialistaDto = EspecialistaDto.from(especialista);
		} else {
			// Los demás solo tienen acceso a una parte
			especialistaDto = EspecialistaDto.construirEspecialistaPublico(especialista);
		}
		return ResponseEntity.ok(especialistaDto);
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
		if (JwtUtils.getIdUsuario().equals(id)) {
			// Solo el porpio especialista tiene acceso a toda su información
			servicioEspecialistas.eliminarEspecialista(id);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPacientes(@Valid Collection<String> ids, @Valid String id) throws Exception {
		if (JwtUtils.getIdUsuario().equals(id)) {
			servicioEspecialistas.agregarPacientes(id, ids);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPaciente(String idEspecialista, String idPaciente) throws Exception {
		if (JwtUtils.getIdUsuario().equals(idEspecialista)) {
			servicioEspecialistas.eliminarPacientes(idEspecialista, List.of(idPaciente));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		if (JwtUtils.getIdUsuario().equals(id)) {
			servicioEspecialistas.agregarPlantillas(id, ids);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPlantilla(String idEspecialista, String idPlantilla) throws Exception {
		if (JwtUtils.getIdUsuario().equals(idEspecialista)) {
			servicioEspecialistas.eliminarPlantillas(idEspecialista, List.of(idPlantilla));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.noContent().build();
	}
}
