package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.repositorio.RepositorioAlertas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionAlertas implements IServicioObtencionAlertas {

	// Atributos
	
	private RepositorioAlertas repositorioAlertas;
	
	// Constructores
	
	@Autowired
	public ServicioObtencionAlertas(RepositorioAlertas repositorioAlertas) {
		super();
		this.repositorioAlertas = repositorioAlertas;
	}
	
	// Métodos

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
}
