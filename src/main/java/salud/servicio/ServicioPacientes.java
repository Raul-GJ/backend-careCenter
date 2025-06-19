package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ServicioPacientes(RepositorioUsuarios repositorioUsuarios, 
			IServicioSeguimientos servicioSeguimientos) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioSeguimientos = servicioSeguimientos;
	}
	
	// Métodos
	
	@Override
	public String altaPaciente(String nombre, String apellidos, String email, String telefono, 
			String contrasenya) throws EntidadNoEncontrada {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellidos == null || apellidos.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		if (!ValidadorEmail.esValido(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (repositorioUsuarios.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Ya existe un usuario con ese email");
		}
		if (contrasenya == null || contrasenya.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		
		Paciente paciente = new Paciente(nombre, apellidos, email, telefono, contrasenya);
		
		String idPaciente = repositorioUsuarios.save(paciente).getId();
		return idPaciente;
	}

	@Override
	public void establecerMedico(String id, Medico Medico) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.setMedicoCabecera(Medico);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void modificarPaciente(String id, String nombre, String apellidos, 
			String email, String telefono) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		
		if (nombre != null && !nombre.isBlank())
			paciente.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			paciente.setApellidos(apellidos);
		if (email != null && !email.isBlank()) {
			if (!ValidadorEmail.esValido(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			paciente.setEmail(email);
		}
		if (telefono != null && !telefono.isBlank())
			paciente.setTelefono(telefono);
		
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarPaciente(String id) throws EntidadNoEncontrada {
		obtenerPaciente(id);
		repositorioUsuarios.deleteById(id);
	}

	@Override
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.agregarSeguimientos(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarEspecialista(especialista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarSeguimiento(seguimiento);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.eliminarSeguimientos(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarEspecialista(especialista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarSeguimiento(seguimiento);
		repositorioUsuarios.save(paciente);
	}

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
