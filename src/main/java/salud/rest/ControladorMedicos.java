package salud.rest;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import salud.modelo.Medico;
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
	
	// Métodos

	@Override
	public ResponseEntity<Void> modificarMedico(@Valid MedicoDto medicoDto, @Valid String id) throws Exception {
		servicioMedicos.modificarMedico(id,
				medicoDto.getNombre(), 
				medicoDto.getApellidos(), 
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
	public ResponseEntity<Collection<MedicoDto>> obtenerMedicos() throws Exception {
		Collection<Medico> medicos = servicioMedicos.obtenerMedicos();
		Collection<MedicoDto> dtos = new LinkedList<MedicoDto>();
		medicos.forEach(a -> dtos.add(MedicoDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarMedico(@Valid String id) throws Exception {
		servicioMedicos.eliminarMedico(id);
		return ResponseEntity.noContent().build();
	}
}
