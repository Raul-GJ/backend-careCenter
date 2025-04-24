package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioEstudios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionAlertas;
import salud.servicio.obtencion.IServicioObtencionEstudios;
import salud.servicio.obtencion.IServicioObtencionPacientes;
import salud.servicio.obtencion.IServicioObtencionSeguimientos;

@Service
@Transactional
public class ServicioEstudios implements IServicioEstudios {
	
	// Atributos
	
	private RepositorioEstudios repositorioEstudios;
	private IServicioObtencionEstudios servicioEstudios;
	private IServicioObtencionPacientes servicioPacientes;
	private IServicioObtencionSeguimientos servicioSeguimientos;
	private IServicioObtencionAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioEstudios(RepositorioEstudios repositorioEstudios, IServicioObtencionEstudios servicioEstudios,
			IServicioObtencionPacientes servicioPacientes, IServicioObtencionSeguimientos servicioSeguimientos,
			IServicioObtencionAlertas servicioAlertas) {
		super();
		this.repositorioEstudios = repositorioEstudios;
		this.servicioEstudios = servicioEstudios;
		this.servicioPacientes = servicioPacientes;
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos

	@Override
	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin) throws EntidadNoEncontrada {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
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
		if (fechaAlta == null) {
			throw new IllegalArgumentException("La fecha de alta no puede ser nula");
		}
		if (fechaAlta.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha de alta no puede ser anterior al dia de hoy");
		}
		if (fechaFin.isBefore(fechaAlta)) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de alta");
		}
		
		Estudio estudio = new Estudio(nombre, descripcion, fechaAlta, fechaFin);
		return repositorioEstudios.save(estudio).getId();
	}

	@Override
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		estudio.agregarPacientes(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		estudio.agregarSeguimientos(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		estudio.agregarAlertas(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		estudio.eliminarPacientes(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		estudio.eliminarSeguimientos(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		estudio.eliminarAlertas(lista);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		
		if (nombre != null && !nombre.isBlank())
			estudio.setNombre(nombre);
		if (descripcion != null && !descripcion.isBlank())
			estudio.setDescripcion(descripcion);
		if (fechaFin != null && fechaFin.isAfter(LocalDateTime.now()))
			estudio.setFechaFin(fechaFin);
		
		repositorioEstudios.save(estudio);
		
	}

	@Override
	public void eliminarEstudio(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioEstudios.deleteById(id);
	}

	@Override
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.agregarPaciente(paciente);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.agregarSeguimiento(seguimiento);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.agregarAlerta(alerta);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.eliminarPaciente(paciente);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.eliminarSeguimiento(seguimiento);
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.eliminarAlerta(alerta);
		repositorioEstudios.save(estudio);
	}

	@Override
	public Estudio obtenerEstudio(String id) throws EntidadNoEncontrada {
		return servicioEstudios.obtenerEstudio(id);
	}

	@Override
	public Collection<Estudio> obtenerEstudios() {
		return servicioEstudios.obtenerEstudios();
	}

	@Override
	public Collection<Estudio> obtenerEstudios(Collection<String> ids) {
		return servicioEstudios.obtenerEstudios(ids);
	}
}
