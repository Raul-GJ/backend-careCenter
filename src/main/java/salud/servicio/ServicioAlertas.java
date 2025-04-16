package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.repositorio.RepositorioAlertas;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionAlertas;

@Service
@Transactional
public class ServicioAlertas implements IServicioAlertas {

	// Atributos
	
	private RepositorioAlertas repositorioAlertas;
	private IServicioObtencionAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioAlertas(RepositorioAlertas repositorioAlertas, IServicioObtencionAlertas servicioAlertas) {
		super();
		this.repositorioAlertas = repositorioAlertas;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos
	
	@Override
	public String altaAlerta(String asunto, String mensaje, LocalDateTime fecha) {
		if (asunto == null || asunto.isEmpty()) {
			throw new IllegalArgumentException("El asunto no puede ser nulo o vacío");
		}
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Alerta alerta = new Alerta(asunto, mensaje, fecha);
		
		return repositorioAlertas.save(alerta).getId();
	}

	@Override
	public void modificarAlerta(String id, String asunto, String mensaje, LocalDateTime fecha)
			throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
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
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Alerta alerta = obtenerAlerta(id);
		
		alerta.setAsunto(asunto);
		alerta.setFecha(fecha);
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
		return servicioAlertas.obtenerAlerta(id);
	}

	@Override
	public Collection<Alerta> obtenerAlertas() {
		return servicioAlertas.obtenerAlertas();
	}

	@Override
	public Collection<Alerta> obtenerAlertas(Collection<String> ids) {
		return servicioAlertas.obtenerAlertas(ids);
	}
}
