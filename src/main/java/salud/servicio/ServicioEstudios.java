package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.RolEstudio;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioEstudios;

@Service
@Transactional
public class ServicioEstudios implements IServicioEstudios {
	
	// Atributos
	
	private RepositorioEstudios repositorioEstudios;
	private IServicioPacientes servicioPacientes;
	private IServicioSeguimientos servicioSeguimientos;
	private IServicioAlertas servicioAlertas;
	private IServicioEspecialistas servicioEspecialistas;
	
	// Constructores
	
	public ServicioEstudios(RepositorioEstudios repositorioEstudios, IServicioPacientes servicioPacientes,
			IServicioSeguimientos servicioSeguimientos, IServicioAlertas servicioAlertas,
			IServicioEspecialistas servicioEspecialistas) {
		super();
		this.repositorioEstudios = repositorioEstudios;
		this.servicioPacientes = servicioPacientes;
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioAlertas = servicioAlertas;
		this.servicioEspecialistas = servicioEspecialistas;
	}
	
	// Métodos

	@Override
	public String altaEstudio(String nombre, String descripcion, LocalDateTime fechaAlta, 
			LocalDateTime fechaFin, String creador) throws EntidadNoEncontrada {
		
		Especialista especialista = servicioEspecialistas.obtenerEspecialista(creador);
		Estudio estudio = new Estudio(nombre, descripcion, fechaAlta, fechaFin, especialista);
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
	public void agregarEspecialistas(String id, Collection<String> especialistas, RolEstudio rol)
			throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Especialista> listaTmp = servicioEspecialistas.obtenerEspecialistas(especialistas);
		Map<Especialista, RolEstudio> lista = new HashMap<Especialista, RolEstudio>();
		listaTmp.forEach(e -> lista.put(e, rol));
		estudio.agregarEspecialistas(lista);
		for (Especialista especialista : listaTmp) {
			servicioEspecialistas.agregarEstudio(especialista.getId(), estudio, rol);
		}
		repositorioEstudios.save(estudio);
	}
	
	@Override
	public void agregarEspecialista(String id, String idEspecialista, String rol) 
			throws EntidadNoEncontrada {
		RolEstudio rolEstudio;
		try {
			rolEstudio = RolEstudio.valueOf(rol);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("El valor " + rol + " no es un rol válido");
		}
		if (rolEstudio.equals(RolEstudio.CREADOR))
			throw new IllegalArgumentException("No puedes agregar un especialista con el rol creador");
		System.out.println(rolEstudio);
		Estudio estudio = obtenerEstudio(id);
		
		Especialista especialista = servicioEspecialistas.obtenerEspecialista(idEspecialista);
		estudio.agregarEspecialista(especialista, rolEstudio);
		servicioEspecialistas.agregarEstudio(idEspecialista, estudio, rolEstudio);
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
	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		Collection<Especialista> lista = servicioEspecialistas.obtenerEspecialistas(especialistas);
		estudio.eliminarEspecialistas(lista);
		for (Especialista especialista : lista) {
			servicioEspecialistas.eliminarEstudio(especialista.getId(), estudio);
		}
		repositorioEstudios.save(estudio);
	}

	@Override
	public void modificarEstudio(String id, String nombre, String descripcion, LocalDateTime fechaFin)
			throws EntidadNoEncontrada {
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
		}
		if (fechaFin == null) {
			throw new IllegalArgumentException("La fecha de fin no puede ser nula");
		}
		if (fechaFin.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior al dia de hoy");
		}
		
		Estudio estudio = obtenerEstudio(id);
		
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
	public void agregarEspecialista(String id, Especialista especialista, RolEstudio rol) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.agregarEspecialista(especialista, rol);
		servicioEspecialistas.agregarEstudio(especialista.getId(), estudio, rol);
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
	public void eliminarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Estudio estudio = obtenerEstudio(id);
		estudio.eliminarEspecialista(especialista);
		servicioEspecialistas.eliminarEstudio(especialista.getId(), estudio);
		repositorioEstudios.save(estudio);
	}
}
