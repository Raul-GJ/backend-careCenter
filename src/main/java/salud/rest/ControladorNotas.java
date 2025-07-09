package salud.rest;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.auth.JwtUtils;
import salud.modelo.NotaPaciente;
import salud.rest.dto.usuario.CrearNotaPacienteDto;
import salud.rest.dto.usuario.NotaPacienteDto;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.IServicioNotas;

@RestController
@RequestMapping("/salud/api/usuarios/pacientes/notas")
public class ControladorNotas implements NotasApi {

	// Atributos
	
	private IServicioNotas servicioNotas;
	
	// Constructores
	
	public ControladorNotas(IServicioNotas servicioNotas) {
		super();
		this.servicioNotas = servicioNotas;
	}
	
	// Métodos
	
	@Override
	public ResponseEntity<Void> crearNota(@Valid CrearNotaPacienteDto notaDto) throws Exception {
		String id = servicioNotas.altaNota(
				notaDto.getSanitario(), 
				notaDto.getAsunto(), 
				notaDto.getContenido(), 
				notaDto.getPrivado());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> modificarNota(@Valid NotaPacienteDto notaDto, String id) throws Exception {
		servicioNotas.modificarNota(id, 
				notaDto.getAsunto(), 
				notaDto.getContenido(), 
				notaDto.isPrivado());
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<NotaPacienteDto> obtenerNota(String id) throws Exception {
		NotaPacienteDto notaDto = NotaPacienteDto.from(servicioNotas.obtenerNota(id));
		if (JwtUtils.isPaciente() && notaDto.isPrivado()) {
			throw new EntidadNoEncontrada(id);
		}
		return ResponseEntity.ok(notaDto);
	}

	@Override
	public ResponseEntity<Collection<NotaPacienteDto>> obtenerNotas() throws Exception {
		Collection<NotaPaciente> notas = servicioNotas.obtenerNotas();
		Collection<NotaPacienteDto> dtos = new LinkedList<NotaPacienteDto>();
		notas.forEach(n -> dtos.add(NotaPacienteDto.from(n)));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> eliminarNota(String id) throws Exception {
		servicioNotas.eliminarNota(id);
		return ResponseEntity.noContent().build();
	}

}
