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

import salud.modelo.Paciente;
import salud.rest.dto.usuario.PacienteDto;
import salud.servicio.IServicioPacientes;

@RestController
@RequestMapping("/salud/api/usuarios/pacientes")
public class ControladorPacientes implements PacientesApi {
	
	// Atributos
	
	private IServicioPacientes servicioPacientes;
	
	// Constructores
	
	public ControladorPacientes(IServicioPacientes servicioPacientes) {
		super();
		this.servicioPacientes = servicioPacientes;
	}
	
	// Métodos

	@Override
	public ResponseEntity<Void> modificarPaciente(@Valid PacienteDto pacienteDto, @Valid String id) throws Exception {
		servicioPacientes.modificarPaciente(id,
				pacienteDto.getNombre(), 
				pacienteDto.getApellidos(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(),
				LocalDate.parse(pacienteDto.getFechaNacimiento(), DateTimeFormatter.BASIC_ISO_DATE),
				pacienteDto.getSexo(),
				pacienteDto.getDireccion(),
				pacienteDto.getDni(),
				pacienteDto.getNss());
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PacienteDto> obtenerPaciente(@Valid String id) throws Exception {
		PacienteDto pacienteDto = PacienteDto.from(servicioPacientes.obtenerPaciente(id));
		return ResponseEntity.ok(pacienteDto);
	}

	@Override
	public ResponseEntity<Collection<PacienteDto>> obtenerPacientes() throws Exception {
		Collection<Paciente> pacientes = servicioPacientes.obtenerPacientes();
		Collection<PacienteDto> dtos = new LinkedList<PacienteDto>();
		pacientes.forEach(a -> dtos.add(PacienteDto.from(a)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarPaciente(@Valid String id) throws Exception {
		servicioPacientes.eliminarPaciente(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		servicioPacientes.agregarSeguimientos(id, seguimientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarSeguimiento(String idPaciente, String idSeguimiento)
			throws Exception {
		servicioPacientes.eliminarSeguimientos(idPaciente, List.of(idSeguimiento));
		return ResponseEntity.noContent().build();
	}
	
}
