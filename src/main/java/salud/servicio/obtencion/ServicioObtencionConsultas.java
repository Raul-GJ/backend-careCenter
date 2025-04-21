package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Consulta;
import salud.repositorio.RepositorioConsultas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionConsultas implements IServicioObtencionConsultas {
	
	// Atributos
	
	private RepositorioConsultas repositorioConsultas;
	
	// Constructores
	
	public ServicioObtencionConsultas(RepositorioConsultas repositorioConsultas) {
		super();
		this.repositorioConsultas = repositorioConsultas;
	}
	
	// Métodos

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
	public Collection<Consulta> obtenerConsultas() {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findAll().forEach(consulta -> consultas.add(consulta));
		return consultas;
	}
	
	@Override
	public Collection<Consulta> obtenerConsultas(Collection<String> ids) {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findAllById(ids).forEach(consulta -> consultas.add(consulta));
		return consultas;
	}

	@Override
	public Collection<Consulta> obtenerConsultasPaciente(String id) {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findByEmisor(id).forEach(consulta -> consultas.add(consulta));
		return consultas;
	}

	@Override
	public Collection<Consulta> obtenerConsultasSanitario(String id) {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findByReceptor(id).forEach(consulta -> consultas.add(consulta));
		return consultas;
	}
}
