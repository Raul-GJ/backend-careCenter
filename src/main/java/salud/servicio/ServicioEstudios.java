package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioAlertas;
import salud.repositorio.RepositorioEstudios;
import salud.repositorio.RepositorioPacientes;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.dto.estudio.EstudioDto;

@Service
@Transactional
public class ServicioEstudios implements IServicioEstudios {
	
	// Atributos
	
	private RepositorioEstudios repositorioEstudios;
	private RepositorioPacientes repositorioPacientes;
	private RepositorioSeguimientos repositorioSeguimientos;
	private RepositorioAlertas repositorioAlertas;
	
	// Constructores
	
	public ServicioEstudios(RepositorioEstudios repositorioEstudios, 
			RepositorioPacientes repositorioPacientes, RepositorioSeguimientos repositorioSeguimientos,
			RepositorioAlertas repositorioAlertas) {
		super();
		this.repositorioEstudios = repositorioEstudios;
		this.repositorioPacientes = repositorioPacientes;
		this.repositorioSeguimientos = repositorioSeguimientos;
		this.repositorioAlertas = repositorioAlertas;
	}
	
	// Métodos
	
	@Override
	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin, String creador) throws EntidadNoEncontrada {
		if (creador == null || creador.isEmpty()) {
			throw new IllegalArgumentException("El creador no puede ser nulo o vacío");
		}
		
		Estudio estudio = new Estudio(nombre, descripcion, fechaAlta, fechaFin);
		return repositorioEstudios.save(estudio).getId();
	}

	@Override
	public void asignarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Paciente> lista = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(pacientes).forEach(p -> lista.add(p));
		estudio.setPacientes(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void asignarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Seguimiento> lista = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAllById(seguimientos).forEach(s -> lista.add(s));
		estudio.setSeguimientos(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void asignarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Alerta> lista = new LinkedList<Alerta>();
		repositorioAlertas.findAllById(alertas).forEach(a -> lista.add(a));
		estudio.setAlertas(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Paciente> lista = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(pacientes).forEach(p -> lista.add(p));
		estudio.removePacientes(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Seguimiento> lista = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAllById(seguimientos).forEach(s -> lista.add(s));
		estudio.removeSeguimientos(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		Collection<Alerta> lista = new LinkedList<Alerta>();
		repositorioAlertas.findAllById(alertas).forEach(a -> lista.add(a));
		estudio.removeAlertas(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
		}
		if (fechaFin == null) {
			throw new IllegalArgumentException("La fecha de fin no puede ser nula");
		}
		if (fechaFin.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior al dia de hoy");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		
		estudio.setNombre(nombre);
		estudio.setDescripcion(descripcion);
		estudio.setFechaFin(fechaFin);
		
		repositorioEstudios.save(estudio);
		
	}

	@Override
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Estudio> optional = repositorioEstudios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Estudio estudio = optional.get();
		
		return estudio;
	}
	
	@Override
	public Collection<EstudioDto> obtenerEstudios() {
		Collection<EstudioDto> estudios = new LinkedList<EstudioDto>();
		repositorioEstudios.findAll().forEach(estudio -> estudios.add(EstudioDto.from(estudio)));
		return estudios;
	}
	
	@Override
	public Collection<EstudioDto> obtenerEstudios(Collection<String> ids) {
		Collection<EstudioDto> estudios = new LinkedList<EstudioDto>();
		repositorioEstudios.findAllById(ids).forEach(estudio -> estudios.add(EstudioDto.from(estudio)));
		return estudios;
	}

	@Override
	public Page<EstudioDto> obtenerEstudiosPaginado(Pageable pageable) {
		return repositorioEstudios.findAll(pageable).map(estudio -> {
			EstudioDto dto = EstudioDto.from(estudio);
			return dto;
		});
	}

	@Override
	public void eliminarEstudio(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioEstudios.deleteById(id);
	}
}
