package salud.servicio.obtencion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Paciente;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioObtencionPacientes implements IServicioObtencionPacientes {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	
	// Constructores
	
	public ServicioObtencionPacientes(RepositorioUsuarios repositorioUsuarios) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	// Métodos
	
	@Override
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		if (!optional.get().getTipo().equals(TipoUsuario.PACIENTE))
			throw new IllegalArgumentException("El usuario con id " + id + 
					" no es un paciente");
		Paciente paciente = (Paciente) optional.get();
		return paciente;
	}

	@Override
	public Collection<Paciente> obtenerPacientes() {
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		Collection<Usuario> usuarios = repositorioUsuarios.findByTipo(TipoUsuario.PACIENTE);
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.PACIENTE))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un paciente");
			pacientes.add((Paciente) usuario);
		}
		return pacientes;
	}
	
	@Override
	public Collection<Paciente> obtenerPacientes(Collection<String> ids) {
		Collection<Paciente> pacientes = new LinkedList<Paciente>();
		Collection<Usuario> usuarios = new LinkedList<Usuario>();
		repositorioUsuarios.findAllById(ids).forEach(u -> usuarios.add(u));
		for (Usuario usuario : usuarios) {
			if (!usuario.getTipo().equals(TipoUsuario.PACIENTE))
				throw new IllegalArgumentException("El usuario con id " + usuario.getId() + 
						" no es un paciente");
			pacientes.add((Paciente) usuario);
		}
		return pacientes;
	}
}
