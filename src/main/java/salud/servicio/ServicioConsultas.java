package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Respuesta;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioConsultas;
import salud.repositorio.RepositorioEspecialistas;
import salud.repositorio.RepositorioPacientes;
import salud.rest.dto.consulta.ConsultaDto;

@Service
@Transactional
public class ServicioConsultas implements IServicioConsultas {
	
	// Atributos
	
	private RepositorioConsultas repositorioConsultas;
	private RepositorioPacientes repositorioPacientes;
	private RepositorioEspecialistas repositorioEspecialistas;
	
	// Constructores
	
	public ServicioConsultas(RepositorioConsultas repositorioConsultas, RepositorioPacientes repositorioPacientes,
			RepositorioEspecialistas repositorioEspecialistas) {
		super();
		this.repositorioConsultas = repositorioConsultas;
		this.repositorioPacientes = repositorioPacientes;
		this.repositorioEspecialistas = repositorioEspecialistas;
	}
	
	// Métodos
	
	@Override
	public String altaConsulta(String asunto, String mensaje, String emisor, String receptor) 
			throws EntidadNoEncontrada {
		if (asunto == null || asunto.isEmpty()) {
			throw new IllegalArgumentException("El asunto no puede ser nulo o vacío");
		}
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		
		Optional<Paciente> optionalPaciente = repositorioPacientes.findById(emisor);
		if (optionalPaciente.isEmpty()) {
			throw new EntidadNoEncontrada(emisor);
		}
		Paciente paciente = optionalPaciente.get();
		
		Optional<Especialista> optionalEspecialista = repositorioEspecialistas.findById(receptor);
		if (optionalEspecialista.isEmpty()) {
			throw new EntidadNoEncontrada(receptor);
		}
		Especialista especialista = optionalEspecialista.get();
		
		Consulta consulta = new Consulta(asunto, mensaje, paciente, especialista);
		return repositorioConsultas.save(consulta).getId();
	}

	@Override
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Consulta> optional = repositorioConsultas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Consulta consulta = optional.get();
		return consulta;
	}

	@Override
	public Collection<ConsultaDto> obtenerConsultas() {
		Collection<ConsultaDto> consultas = new LinkedList<ConsultaDto>();
		repositorioConsultas.findAll().forEach(consulta -> consultas.add(ConsultaDto.from(consulta)));
		return consultas;
	}
	
	@Override
	public Collection<ConsultaDto> obtenerConsultas(Collection<String> ids) {
		Collection<ConsultaDto> consultas = new LinkedList<ConsultaDto>();
		repositorioConsultas.findAllById(ids).forEach(consulta -> consultas.add(ConsultaDto.from(consulta)));
		return consultas;
	}
	
	@Override
	public Page<ConsultaDto> obtenerConsultasPaginado(Pageable pageable) {
		
		return repositorioConsultas.findAll(pageable).map(consulta -> {
			ConsultaDto dto = ConsultaDto.from(consulta);
			return dto;
		});
	}

	@Override
	public void eliminarConsulta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioConsultas.deleteById(id);
	}

	@Override
	public void responderConsulta(String id, String mensaje) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		Respuesta respuesta = new Respuesta(mensaje, LocalDateTime.now());
		
		Optional<Consulta> optional = repositorioConsultas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Consulta consulta = optional.get();
		
		consulta.setRespuesta(respuesta);
		
		repositorioConsultas.save(consulta);
	}
}
