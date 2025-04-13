package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioAlertas;

@Service
@Transactional
public class ServicioAlertas implements IServicioAlertas {

	// Atributos
	
	private RepositorioAlertas repositorioAlertas;
	
	// Constructores
	
	@Autowired
	public ServicioAlertas(RepositorioAlertas repositorioAlertas) {
		super();
		this.repositorioAlertas = repositorioAlertas;
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
		
		Optional<Alerta> optional = repositorioAlertas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Alerta alerta = optional.get();
		
		alerta.setAsunto(asunto);
		alerta.setFecha(fecha);
		alerta.setMensaje(mensaje);
		
		repositorioAlertas.save(alerta);

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
	public void eliminarAlerta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioAlertas.deleteById(id);
	}
}
