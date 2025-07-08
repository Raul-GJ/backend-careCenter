package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioEstudios;
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioEstudios implements IServicioEstudios {
	
	// Atributos
	
	private RepositorioEstudios repositorioEstudios;
	private IServicioPacientes servicioPacientes;
	private IServicioSeguimientos servicioSeguimientos;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioEstudios(RepositorioEstudios repositorioEstudios,
			IServicioPacientes servicioPacientes, IServicioSeguimientos servicioSeguimientos,
			IServicioAlertas servicioAlertas) {
		super();
		this.repositorioEstudios = repositorioEstudios;
		this.servicioPacientes = servicioPacientes;
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos

	@Override
	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaInicio, 
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
		if (fechaInicio == null) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
		}
		if (fechaInicio.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser anterior al dia de hoy");
		}
		if (fechaFin.isBefore(fechaInicio)) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
		}
		
		Estudio estudio = new Estudio(nombre, descripcion, fechaInicio, fechaFin);
		return repositorioEstudios.save(estudio).getId();
	}

	@Override
	public void agregarPacientes(String id, String idEspecialista, Collection<String> pacientes) 
			throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		for (Paciente paciente : estudio.getPacientes()) {
			if (pacientes.contains(paciente.getId()))
				throw new ConflictException("No puedes agregar pacientes que ya están dentro de este estudio");
		}
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		estudio.agregarPacientes(lista);
		for (Paciente paciente : lista) {
			Collection<Seguimiento> seguimientos = estudio.getSeguimientos();
			Collection<String> seguimientos2 = new LinkedList<String>();
			for (Seguimiento seguimiento : seguimientos) {
				// Hacer una copia para asignarla al paciente
				String idSeguimiento = servicioSeguimientos.altaSeguimiento(
						seguimiento.getFecha(), seguimiento.getPlazo(), 
						seguimiento.getFormulario().getPlantilla().getId(), 
						seguimiento.getMotivo(), seguimiento.getId());
				seguimientos2.add(idSeguimiento);
			}
			servicioPacientes.agregarSeguimientos(paciente.getId(), seguimientos2);
			for (Alerta alerta : estudio.getAlertas()) {
				servicioAlertas.altaAlerta(idEspecialista, paciente.getId(), false, 
						alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha(), alerta.getId());
			}
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarSeguimientos(String id, String idEspecialista, Collection<String> seguimientos) 
			throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		for (Seguimiento seguimiento : estudio.getSeguimientos()) {
			if (seguimientos.contains(seguimiento.getId()))
				throw new ConflictException("No puedes agregar seguimientos que ya están dentro de este estudio");
		}
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		estudio.agregarSeguimientos(lista);
		for (Paciente paciente : estudio.getPacientes()) {
			Collection<String> seguimientos2 = new LinkedList<String>();
			for (Seguimiento seguimiento : lista) {
				// Hacer una copia para asignarla al paciente
				String idSeguimiento = servicioSeguimientos.altaSeguimiento(
						seguimiento.getFecha(), seguimiento.getPlazo(), 
						seguimiento.getFormulario().getPlantilla().getId(), 
						seguimiento.getMotivo(), seguimiento.getId());
				seguimientos2.add(idSeguimiento);
			}
			servicioPacientes.agregarSeguimientos(paciente.getId(), seguimientos2);
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void agregarAlertas(String id, String idEspecialista, Collection<String> alertas) 
			throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		for (Alerta alerta : estudio.getAlertas()) {
			if (alertas.contains(alerta.getId()))
				throw new ConflictException("No puedes agregar alertas que ya están dentro de este estudio");
		}
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		estudio.agregarAlertas(lista);
		for (Paciente paciente : estudio.getPacientes()) {
			for (Alerta alerta : lista) {
				servicioAlertas.altaAlerta(idEspecialista, paciente.getId(), false, 
						alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha(), alerta.getId());
			}
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<String> idPacientes = estudio.getPacientes().stream().map(p -> p.getId()).toList();
		for (String idPaciente : pacientes) {
			if (!idPacientes.contains(idPaciente))
				throw new ConflictException("No puedes eliminar pacientes que no están dentro de este estudio");
		}
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		estudio.eliminarPacientes(lista);
		for (Paciente paciente : lista) {
			for (Seguimiento seguimiento : estudio.getSeguimientos()) {
				servicioPacientes.eliminarSeguimientosGrupo(paciente.getId(), seguimiento.getId());
			}
			for (Alerta alerta : estudio.getAlertas()) {
				for (Alerta alerta2 : servicioAlertas.obtenerAlertasUsuario(paciente.getId())) {
					if (alerta2.getIdGrupo() != null && alerta2.getIdGrupo().equals(alerta.getId()))
						servicioAlertas.eliminarAlerta(alerta2.getId());
				}
			}
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<String> idSeguimientos = estudio.getSeguimientos().stream().map(p -> p.getId()).toList();
		for (String idSeguimiento : seguimientos) {
			if (!idSeguimientos.contains(idSeguimiento))
				throw new ConflictException("No puedes eliminar seguimientos que no están dentro de este estudio");
		}
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		estudio.eliminarSeguimientos(lista);
		for (String idSeguimiento : seguimientos) {
			for (Paciente paciente : estudio.getPacientes()) {
				servicioPacientes.eliminarSeguimientosGrupo(paciente.getId(), idSeguimiento);
			}
			servicioSeguimientos.eliminarSeguimientosGrupo(idSeguimiento);
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<String> idAlertas = estudio.getAlertas().stream().map(p -> p.getId()).toList();
		for (String idAlerta : alertas) {
			if (!idAlertas.contains(idAlerta))
				throw new ConflictException("No puedes eliminar alertas que no están dentro de este estudio");
		}
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		estudio.eliminarAlertas(lista);
		for (Alerta alerta : lista) {
			servicioAlertas.eliminarAlertasGrupo(alerta.getId());
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaInicio,
			LocalDateTime fechaFin)
			throws EntidadNoEncontrada {
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
		if (fechaInicio == null) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
		}
		if (fechaInicio.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser anterior al dia de hoy");
		}
		if (fechaFin.isBefore(fechaInicio)) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
		}
		Estudio estudio = obtenerEstudio(id);
		
		if (nombre != null && !nombre.isBlank())
			estudio.setNombre(nombre);
		if (descripcion != null && !descripcion.isBlank())
			estudio.setDescripcion(descripcion);
		if (fechaInicio != null && fechaInicio.isAfter(LocalDateTime.now()))
			estudio.setFechaFin(fechaFin);
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
	public Collection<Estudio> obtenerEstudios() {
		Collection<Estudio> estudios = new LinkedList<Estudio>();
		repositorioEstudios.findAll().forEach(estudio -> estudios.add(estudio));
		return estudios;
	}
	
	@Override
	public Collection<Estudio> obtenerEstudios(Collection<String> ids) {
		Collection<Estudio> estudios = new LinkedList<Estudio>();
		repositorioEstudios.findAllById(ids).forEach(estudio -> estudios.add(estudio));
		return estudios;
	}
}
