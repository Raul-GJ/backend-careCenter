package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.NotaPaciente;
import salud.modelo.Sanitario;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioNotas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioNotas implements IServicioNotas {

	private RepositorioNotas repositorioNotas;
	private IServicioUsuarios servicioUsuarios;
	
	public ServicioNotas(RepositorioNotas repositorioNotas, IServicioUsuarios servicioUsuarios) {
		this.repositorioNotas = repositorioNotas;
		this.servicioUsuarios = servicioUsuarios;
	}

	@Override
	public String altaNota(String idSanitario, String asunto, String contenido, Boolean privado) 
			throws EntidadNoEncontrada {
		if (idSanitario == null || idSanitario.isBlank()) {
			throw new IllegalArgumentException("El idSanitario no puede ser nulo o vacío");
		}
		if (asunto == null || asunto.isBlank()) {
			throw new IllegalArgumentException("El asunto no puede ser nulo o vacío");
		}
		if (contenido == null || contenido.isBlank()) {
			throw new IllegalArgumentException("El contenido no puede ser nulo o vacío");
		}
		if (privado == null)
			privado = true;
		
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(idSanitario);
		if (!(usuario instanceof Sanitario))
			throw new EntidadNoEncontrada("No existe el sanitario con id " + idSanitario);
		Sanitario sanitario = (Sanitario) usuario;
		
		NotaPaciente nota = new NotaPaciente(sanitario, asunto, contenido, privado);
		return repositorioNotas.save(nota).getId();
	}

	@Override
	public void modificarNota(String id, String asunto, String contenido, Boolean privado) 
			throws EntidadNoEncontrada {
		NotaPaciente nota = obtenerNota(id);
		
		if (asunto != null && !asunto.isBlank()) {
			nota.setAsunto(asunto);
		}
		if (contenido != null && !contenido.isBlank()) {
			nota.setContenido(contenido);
		}
		if (privado != null)
			nota.setPrivado(privado);
		
		repositorioNotas.save(nota);
	}

	@Override
	public NotaPaciente obtenerNota(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<NotaPaciente> optional = repositorioNotas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		NotaPaciente nota = optional.get();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isPaciente = auth.getAuthorities().stream().anyMatch(
				a -> a.getAuthority().equals(TipoUsuario.PACIENTE.toString()));
		if (isPaciente && nota.isPrivado())
			throw new EntidadNoEncontrada(id);
		
		return nota;
	}

	@Override
	public Collection<NotaPaciente> obtenerNotas() {
		Collection<NotaPaciente> notas = new LinkedList<NotaPaciente>();
		repositorioNotas.findAll().forEach(nota -> notas.add(nota));
		return notas;
	}

	@Override
	public void eliminarNota(String id) throws EntidadNoEncontrada {
		obtenerNota(id);
		repositorioNotas.deleteById(id);
	}
	
	

}
