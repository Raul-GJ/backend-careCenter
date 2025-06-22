package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorCampos;

@Service
@Transactional
public class ServicioUsuarios implements IServicioUsuarios {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioUsuarios(RepositorioUsuarios repositorioUsuarios, IServicioAlertas servicioAlertas) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos

	@Override
	public Usuario obtenerUsuarioPorId(String id) throws EntidadNoEncontrada {
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Usuario usuario = optional.get();
		return usuario;
	}

	@Override
	public Usuario obtenerUsuarioPorCorreo(String correo) throws EntidadNoEncontrada {
		Optional<Usuario> optional = repositorioUsuarios.findByEmail(correo);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(correo);
		}
		Usuario usuario = optional.get();
		return usuario;
	}

	@Override
	public Collection<Usuario> obtenerUsuarios() throws EntidadNoEncontrada {
		Collection<Usuario> usuarios = new LinkedList<Usuario>();
		repositorioUsuarios.findAll().forEach(u -> usuarios.add(u));
		return usuarios;
	}

	@Override
	public void modificarUsuario(String id, String nombre, String apellidos, String email, String telefono)
			throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(id);
		
		if (nombre != null && !nombre.isBlank())
			usuario.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			usuario.setApellidos(apellidos);
		if (email != null && !email.isBlank()) {
			if (!ValidadorCampos.validarEmail(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			usuario.setEmail(email);
		}
		if (telefono != null && !telefono.isBlank())
			usuario.setTelefono(telefono);
		
		repositorioUsuarios.save(usuario);
	}

	@Override
	public void eliminarUsuario(String id) throws EntidadNoEncontrada {
		obtenerUsuarioPorId(id); // Para comprobar que existe
		repositorioUsuarios.deleteById(id);
	}
	
	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(id);
		for (Alerta alerta : usuario.getAlertas()) {
			if (alertas.contains(alerta.getId()))
				throw new ConflictException("No puedes agregar alertas que ya están en tu lista de alertas");
		}
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		usuario.agregarAlertas(lista);
		repositorioUsuarios.save(usuario);
	}

	@Override
	public void agregarAlerta(String idUsuario, String idAlerta) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(idUsuario);
		for (Alerta alerta : usuario.getAlertas()) {
			if (alerta.getId().equals(idAlerta))
				throw new ConflictException("No puedes agregar alertas que ya están en tu lista de alertas");
		}
		Alerta alerta = servicioAlertas.obtenerAlerta(idAlerta);
		usuario.agregarAlerta(alerta);
		repositorioUsuarios.save(usuario);
	}

	@Override
	public void agregarAlerta(String idUsuario, Alerta alerta) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(idUsuario);
		if (usuario.getAlertas().contains(alerta))
			throw new ConflictException("No puedes agregar alertas que ya están en tu lista de alertas");
		usuario.agregarAlerta(alerta);
		repositorioUsuarios.save(usuario);
	}
	
	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		usuario.eliminarAlertas(lista);
		repositorioUsuarios.save(usuario);
	}
	
	@Override
	public void eliminarAlerta(String idUsuario, String idAlerta) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(idUsuario);
		Alerta alerta = servicioAlertas.obtenerAlerta(idAlerta);
		usuario.eliminarAlerta(alerta);
		repositorioUsuarios.save(usuario);
	}

	@Override
	public void eliminarAlerta(String idUsuario, Alerta alerta) throws EntidadNoEncontrada {
		Usuario usuario = obtenerUsuarioPorId(idUsuario);
		usuario.eliminarAlerta(alerta);
		repositorioUsuarios.save(usuario);
	}

}
