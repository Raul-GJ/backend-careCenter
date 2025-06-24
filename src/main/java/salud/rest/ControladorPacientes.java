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
		if (JwtUtils.getIdUsuario().equals(id)) {
			servicioPacientes.modificarPaciente(id,
				pacienteDto.getNombre(), 
				pacienteDto.getApellidos(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(),
				LocalDate.parse(pacienteDto.getFechaNacimiento(), DateTimeFormatter.ISO_DATE),
				pacienteDto.getSexo(),
				pacienteDto.getDireccion(),
				pacienteDto.getDni(),
				pacienteDto.getNss());
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PacienteDto> obtenerPaciente(@Valid String id) throws Exception {
		Paciente paciente = servicioPacientes.obtenerPaciente(id);
		PacienteDto pacienteDto;
		
		if (JwtUtils.isPaciente()) {
			// El paciente no debe poder ver las notas privadas que le ponen los especialistas
			if (JwtUtils.getIdUsuario().equals(id)) {
				pacienteDto = PacienteDto.construirSinNotasPrivadas(paciente);
			} else {
				// Un paciente no tiene acceso a la información de otros pacientes
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} else {
			pacienteDto = PacienteDto.from(paciente);
		}
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
		if (JwtUtils.getIdUsuario().equals(id)) {
			servicioPacientes.eliminarPaciente(id);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
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

	@Override
	public ResponseEntity<Void> agregarAlergias(@Valid Collection<String> alergias, String id) 
			throws Exception {
		servicioPacientes.agregarAlergias(id, alergias);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarAlergia(String idPaciente, String alergia) throws Exception {
		servicioPacientes.eliminarAlergia(idPaciente, alergia);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarTratamientos(@Valid Collection<String> tratamientos, String id)
			throws Exception {
		servicioPacientes.agregarTratamientos(id, tratamientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarTratamiento(String idPaciente, String tratamiento) throws Exception {
		servicioPacientes.eliminarTratamiento(idPaciente, tratamiento);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarNotas(@Valid Collection<String> notas, String id) throws Exception {
		servicioPacientes.agregarNotas(id, notas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarNota(String idPaciente, String idNota) throws Exception {
		servicioPacientes.eliminarNota(idPaciente, idNota);
		return ResponseEntity.noContent().build();
	}
	
}
