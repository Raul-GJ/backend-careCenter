package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Paciente;
import salud.rest.dto.usuario.CrearPacienteDto;
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
	public ResponseEntity<PacienteDto> altaPaciente(@Valid CrearPacienteDto pacienteDto) throws Exception {
		String id = servicioPacientes.altaPaciente(pacienteDto.getNombre(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getApellido2(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(), 
				pacienteDto.getMedicoCabecera());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarPaciente(@Valid PacienteDto pacienteDto, @Valid String id) throws Exception {
		servicioPacientes.modificarPaciente(id,
				pacienteDto.getNombre(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getApellido2(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(), 
				pacienteDto.getMedicoCabecera());
		
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
	public ResponseEntity<Void> agregarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		servicioPacientes.agregarAlertas(id, alertas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarEspecialistas(@Valid Collection<String> especialistas, @Valid String id)
			throws Exception {
		servicioPacientes.agregarEspecialistas(id, especialistas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> agregarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		servicioPacientes.agregarSeguimientos(id, seguimientos);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarAlertas(@Valid Collection<String> alertas, @Valid String id) throws Exception {
		servicioPacientes.eliminarAlertas(id, alertas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarEspecialistas(@Valid Collection<String> especialistas, @Valid String id)
			throws Exception {
		servicioPacientes.eliminarEspecialistas(id, especialistas);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> eliminarSeguimientos(@Valid Collection<String> seguimientos, @Valid String id)
			throws Exception {
		servicioPacientes.eliminarSeguimientos(id, seguimientos);
		return ResponseEntity.noContent().build();
	}
	
}
