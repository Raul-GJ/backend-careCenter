package salud.rest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.modelo.Formulario;
import salud.rest.dto.formulario.FormularioDto;
import salud.rest.dto.usuario.PacienteDto;
import salud.servicio.IServicioEspecialistas;
import salud.servicio.IServicioFormulariosPlantilla;
import salud.servicio.IServicioMedicos;
import salud.servicio.IServicioPacientes;

@RestController
@RequestMapping("/salud/api/usuarios/pacientes")
public class ControladorPacientes implements PacientesApi {
	
	// Atributos
	
	private IServicioPacientes servicioPacientes;
	private IServicioMedicos servicioMedicos;
	private IServicioEspecialistas servicioEspecialistas;
	private IServicioFormulariosPlantilla servicioFormulariosPlantilla;
	
	// Constructores
	
	public ControladorPacientes(IServicioPacientes servicioPacientes,
			IServicioMedicos servicioMedicos, IServicioEspecialistas servicioEspecialistas,
			IServicioFormulariosPlantilla servicioFormulariosPlantilla) {
		super();
		this.servicioPacientes = servicioPacientes;
		this.servicioMedicos = servicioMedicos;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioFormulariosPlantilla = servicioFormulariosPlantilla;
	}
	
	// Métodos
	
	// Pacientes
	
	@Override
	public ResponseEntity<PacienteDto> altaPaciente(@Valid PacienteDto pacienteDto) throws Exception {
		String id = servicioPacientes.altaPaciente(pacienteDto.getNombre(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(), 
				servicioMedicos.obtenerMedico(pacienteDto.getMedicoCabecera()));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarPaciente(@Valid PacienteDto pacienteDto, @Valid String id) throws Exception {
		servicioPacientes.modificarPaciente(id,
				pacienteDto.getNombre(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getApellido1(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(), 
				servicioMedicos.obtenerMedico(pacienteDto.getMedicoCabecera()));
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PacienteDto> obtenerPaciente(@Valid String id) throws Exception {
		PacienteDto pacienteDto = PacienteDto.from(servicioPacientes.obtenerPaciente(id));
		return ResponseEntity.ok(pacienteDto);
	}

	@Override
	public ResponseEntity<Collection<PacienteDto>> obtenerPacientes() throws Exception {
		Collection<PacienteDto> pacientes = servicioPacientes.obtenerPacientes();
		return ResponseEntity.ok(pacientes);
	}

	@Override
	public ResponseEntity<Void> eliminarPaciente(@Valid String id) throws Exception {
		servicioPacientes.eliminarPaciente(id);
		return ResponseEntity.noContent().build();
	}
	
	// Formularios
	
	public ResponseEntity<FormularioDto> crearFormulario(String idUsr, 
			FormularioDto formularioDto) throws Exception {
		String id = servicioPacientes.altaFormulario(idUsr, 
				servicioFormulariosPlantilla.obtenerFormulario(formularioDto.getPlantilla()), 
				formularioDto.getDatos());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Void> modificarFormulario(FormularioDto formularioDto, 
			String idUsr, int pos) throws Exception {
		servicioPacientes.modificarFormulario(idUsr, 
				pos, 
				servicioFormulariosPlantilla.obtenerFormulario(formularioDto.getPlantilla()), 
				formularioDto.getDatos());
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<FormularioDto> obtenerFormulario(String idUsr, int pos) 
			throws Exception {
		Formulario formulario = servicioPacientes.obtenerFormulario(idUsr, pos);
		return ResponseEntity.ok(FormularioDto.from(formulario));
	}
	
	public ResponseEntity<Collection<FormularioDto>> obtenerFormularios(String idUsr) 
			throws Exception {
		Collection<FormularioDto> formularios = servicioPacientes.obtenerFormularios(idUsr);
		return ResponseEntity.ok(formularios);
	}

	public ResponseEntity<Void> eliminarFormulario(String idUsr, int pos) 
			throws Exception {
		servicioPacientes.eliminarFormulario(idUsr, pos);
		return ResponseEntity.noContent().build();
	}
}
