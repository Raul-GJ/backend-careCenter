package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionSeguimientos implements IServicioObtencionSeguimientos {

	// Atributos
	
	private RepositorioSeguimientos repositorioSeguimientos;
	
	// Constructores
	
	@Autowired
	public ServicioObtencionSeguimientos(RepositorioSeguimientos repositorioSeguimientos) {
		super();
		this.repositorioSeguimientos = repositorioSeguimientos;
	}
	
	// Métodos

	@Override
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Seguimiento> optional = repositorioSeguimientos.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Seguimiento seguimiento = optional.get();
		
		return seguimiento;
	}

	@Override
	public Collection<Seguimiento> obtenerSeguimientos() {
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAll().forEach(seguimiento -> seguimientos.add(seguimiento));
		return seguimientos;
	}
	
	@Override
	public Collection<Seguimiento> obtenerSeguimientos(Collection<String> ids) {
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAllById(ids).forEach(seguimiento -> seguimientos.add(seguimiento));
		return seguimientos;
	}
}
