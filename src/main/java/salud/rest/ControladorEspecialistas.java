package salud.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
				LocalDate.parse(especialistaDto.getFechaNacimiento(), DateTimeFormatter.BASIC_ISO_DATE),
				especialistaDto.getSexo(),
				especialistaDto.getDireccion(),
				especialistaDto.getDni(),
				especialistaDto.getnCol(),
				especialistaDto.getCentroDeSalud(),
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
	public ResponseEntity<Void> eliminarPaciente(String idEspecialista, String idPaciente) throws Exception {
		servicioEspecialistas.eliminarPacientes(idEspecialista, List.of(idPaciente));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarPlantillas(@Valid Collection<String> ids, @Valid String id) throws Exception {
		servicioEspecialistas.agregarPlantillas(id, ids);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarPlantilla(String idEspecialista, String idPlantilla) throws Exception {
		servicioEspecialistas.eliminarPlantillas(idEspecialista, List.of(idPlantilla));
		return ResponseEntity.noContent().build();
	}
}
