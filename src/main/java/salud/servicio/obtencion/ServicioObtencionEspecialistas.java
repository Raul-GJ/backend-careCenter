package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Especialista;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionEspecialistas implements IServicioObtencionEspecialistas {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	
	// Constructores
	
	public ServicioObtencionEspecialistas(RepositorioUsuarios repositorioUsuarios) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	// Métodos

	@Override
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		if (!optional.get().getTipo().equals(TipoUsuario.ESPECIALISTA)) {
			throw new IllegalArgumentException("El usuario con id " + id + " no es un especialista");
		}
		Especialista especialista = (Especialista) optional.get();
		return especialista;
	}

	@Override
	public Collection<Especialista> obtenerEspecialistas() {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		Collection<Usuario> usuarios = repositorioUsuarios.findByTipo(TipoUsuario.ESPECIALISTA);
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.ESPECIALISTA))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un especialista");
			especialistas.add((Especialista) usuario);
		}
		return especialistas;
	}
	
	@Override
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids) {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		Collection<Usuario> usuarios = new LinkedList<Usuario>();
		repositorioUsuarios.findAllById(ids).forEach(u -> usuarios.add(u));
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.ESPECIALISTA))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un especialista");
			especialistas.add((Especialista) usuario);
		}
		return especialistas;
	}
}
