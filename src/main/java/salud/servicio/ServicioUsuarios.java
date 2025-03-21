package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Usuario;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.dto.usuario.UsuarioDto;

@Service
@Transactional
public class ServicioUsuarios implements IServicioUsuarios {
	
	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	
	// Constructores
	
	public ServicioUsuarios(RepositorioUsuarios repositorioUsuarios) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	// Métodos
	
	@Override
	public String altaUsuario(String nombre, String apellido1, String apellido2, String email, String telefono) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (apellido2 == null || apellido2.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		
		Usuario usuario = new Usuario(nombre, apellido1, apellido2, email, telefono);
		
		return repositorioUsuarios.save(usuario).getId();
	}

	@Override
	public void modificarUsuario(String id, String nombre, String apellido1, String apellido2, String email, String telefono)
			throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (apellido2 == null || apellido2.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Usuario usuario = optional.get();
		usuario.setNombre(nombre);
		usuario.setApellido1(apellido1);
		usuario.setApellido2(apellido2);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		
		repositorioUsuarios.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Usuario usuario = optional.get();
		return usuario;
	}
	
	@Override
	public Collection<UsuarioDto> obtenerUsuarios() {
		Collection<UsuarioDto> usuarios = new LinkedList<UsuarioDto>();
		repositorioUsuarios.findAll().forEach(usuario -> usuarios.add(UsuarioDto.from(usuario)));
		return usuarios;
	}

	@Override
	public Page<UsuarioDto> obtenerUsuariosPaginado(Pageable pageable) {
		return repositorioUsuarios.findAll(pageable).map(usuario -> {
			UsuarioDto dto = UsuarioDto.from(usuario);
			return dto;
		});
	}

	@Override
	public void eliminarUsuario(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioUsuarios.deleteById(id);
	}
}
