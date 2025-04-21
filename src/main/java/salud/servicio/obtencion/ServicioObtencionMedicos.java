package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Medico;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionMedicos implements IServicioObtencionMedicos {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	
	// Constructores
	
	public ServicioObtencionMedicos(RepositorioUsuarios repositorioUsuarios) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	// Métodos

	@Override
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		if (!optional.get().getTipo().equals(TipoUsuario.MEDICO))
			throw new IllegalArgumentException("El usuario con id " + id + 
					" no es un médico");
		Medico medico = (Medico) optional.get();
		return medico;
	}

	@Override
	public Collection<Medico> obtenerMedicos() {
		Collection<Medico> medicos = new LinkedList<Medico>();
		Collection<Usuario> usuarios = repositorioUsuarios.findByTipo(TipoUsuario.MEDICO);
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.MEDICO))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un médico");
			medicos.add((Medico) usuario);
		}
		return medicos;
	}
	
	@Override
	public Collection<Medico> obtenerMedicos(Collection<String> ids) {
		Collection<Medico> medicos = new LinkedList<Medico>();
		Collection<Usuario> usuarios = new LinkedList<Usuario>();
		repositorioUsuarios.findAllById(ids).forEach(u -> usuarios.add(u));
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.MEDICO))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un médico");
			medicos.add((Medico) usuario);
		}
		return medicos;
	}
}
