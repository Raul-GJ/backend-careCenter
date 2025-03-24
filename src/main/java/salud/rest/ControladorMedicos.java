package salud.rest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.rest.dto.usuario.MedicoDto;
import salud.servicio.IServicioMedicos;

@RestController
@RequestMapping("/salud/api/usuarios/medicos")
public class ControladorMedicos implements MedicosApi {

	// Atributos
	
	private IServicioMedicos servicioMedicos;
	
	// Constructores
	
	public ControladorMedicos(IServicioMedicos servicioMedicos) {
		super();
		this.servicioMedicos = servicioMedicos;
	}
	
	@Override
	public ResponseEntity<MedicoDto> altaMedico(@Valid MedicoDto medicoDto) {
		String id = servicioMedicos.altaMedico(medicoDto.getNombre(), 
				medicoDto.getApellido1(), 
				medicoDto.getApellido2(), 
				medicoDto.getEmail(), 
				medicoDto.getTelefono(), 
				medicoDto.getnCol(),
				medicoDto.getAtributoTemporal());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarMedico(@Valid MedicoDto medicoDto, @Valid String id) throws Exception {
		servicioMedicos.modificarMedico(id,
				medicoDto.getNombre(), 
				medicoDto.getApellido1(), 
				medicoDto.getApellido2(), 
				medicoDto.getEmail(), 
				medicoDto.getTelefono(), 
				medicoDto.getnCol(),
				medicoDto.getAtributoTemporal());
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<MedicoDto> obtenerMedico(@Valid String id) throws Exception {
		MedicoDto medico = MedicoDto.from(servicioMedicos.obtenerMedico(id));
		return ResponseEntity.ok(medico);
	}

	@Override
	public ResponseEntity<Collection<MedicoDto>> obtenerMedicos(
			Collection<String> ids) throws Exception {
		if (ids == null || ids.isEmpty()) {
			Collection<MedicoDto> medicos = servicioMedicos.obtenerMedicos();
			return ResponseEntity.ok(medicos);
		}
		Collection<MedicoDto> medicos = servicioMedicos.obtenerMedicos(ids);
		return ResponseEntity.ok(medicos);
	}

	@Override
	public ResponseEntity<Void> eliminarMedico(@Valid String id) throws Exception {
		servicioMedicos.eliminarMedico(id);
		return ResponseEntity.noContent().build();
	}

}
