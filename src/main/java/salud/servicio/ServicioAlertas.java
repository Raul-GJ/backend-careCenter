package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioAlertas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioAlertas implements IServicioAlertas {

	// Atributos
	
	private RepositorioAlertas repositorioAlertas;
	private IServicioUsuarios servicioUsuarios;
	
	// Constructores
	
	public ServicioAlertas(RepositorioAlertas repositorioAlertas, IServicioUsuarios servicioUsuarios) {
		super();
		this.repositorioAlertas = repositorioAlertas;
		this.servicioUsuarios = servicioUsuarios;
	}
	
	// Métodos
	
	@Override
	public String altaAlerta(String idEmisor, String idReceptor, boolean generadaAutomaticamente, 
			String asunto, String mensaje, LocalDateTime fecha, String idGrupo) throws EntidadNoEncontrada {
		if (idEmisor == null || idEmisor.isEmpty()) {
			throw new IllegalArgumentException("El idEmisor no puede ser nulo o vacío");
		}
		if (idReceptor == null || idReceptor.isEmpty()) {
			throw new IllegalArgumentException("El idReceptor no puede ser nulo o vacío");
		}
		if (asunto == null || asunto.isEmpty()) {
			throw new IllegalArgumentException("El asunto no puede ser nulo o vacío");
		}
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now().minusMinutes(1))) { // Se añade cierto márgen de error
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Usuario emisor = servicioUsuarios.obtenerUsuarioPorId(idEmisor);
		Usuario receptor = servicioUsuarios.obtenerUsuarioPorId(idReceptor);
		
		Alerta alerta = new Alerta(emisor, receptor, generadaAutomaticamente, asunto, mensaje, fecha,
				idGrupo);
		
		return repositorioAlertas.save(alerta).getId();
	}

	@Override
	public void modificarAlerta(String id, String asunto, String mensaje, LocalDateTime fecha)
			throws EntidadNoEncontrada {
		Alerta alerta = obtenerAlerta(id);
		
		if (asunto != null && !asunto.isBlank())
			alerta.setAsunto(asunto);
		if (fecha != null)
			alerta.setFecha(fecha);
		if (mensaje != null && !mensaje.isBlank())
			alerta.setMensaje(mensaje);
		
		repositorioAlertas.save(alerta);

	}

	@Override
	public void eliminarAlerta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioAlertas.deleteById(id);
	}

	@Override
	public Alerta obtenerAlerta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Alerta> optional = repositorioAlertas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Alerta alerta = optional.get();
		
		return alerta;
	}

	@Override
	public Collection<Alerta> obtenerAlertas() {
		Collection<Alerta> alertas = new LinkedList<Alerta>();
		repositorioAlertas.findAll().forEach(alerta -> alertas.add(alerta));
		return alertas;
	}
	
	@Override
	public Collection<Alerta> obtenerAlertas(Collection<String> ids) {
		Collection<Alerta> alertas = new LinkedList<Alerta>();
		repositorioAlertas.findAllById(ids).forEach(alerta -> alertas.add(alerta));
		return alertas;
	}
	
	@Override
	public Collection<Alerta> obtenerAlertasUsuario(String id) throws EntidadNoEncontrada {
		servicioUsuarios.obtenerUsuarioPorId(id); // Para lanzar entidad no encontrada
		return repositorioAlertas.findByReceptor(id);
	}

	@Override
	public void leerAlerta(String id) throws EntidadNoEncontrada {
		Alerta alerta = obtenerAlerta(id);
		alerta.setLeida(true);
		repositorioAlertas.save(alerta);
	}

	@Override
	public void eliminarAlertasGrupo(String idGrupo) {
		Collection<Alerta> alertas = repositorioAlertas.findByIdGrupo(idGrupo);
		for (Alerta alerta2 : alertas) {
			repositorioAlertas.deleteById(alerta2.getId());
		}
	}
}
