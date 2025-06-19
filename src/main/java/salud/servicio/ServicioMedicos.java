package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioMedicos implements IServicioMedicos {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioPacientes servicioPacientes;
	
	// Constructores
	
	public ServicioMedicos(RepositorioUsuarios repositorioUsuarios, IServicioPacientes servicioPacientes) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioPacientes = servicioPacientes;
	}
	
	// Métodos
	
	@Override
	public String altaMedico(String nombre, String apellidos, String email, String telefono, 
			String contrasenya, String nCol) {
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
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		
		Medico medico = new Medico(nombre, apellidos, email, telefono, contrasenya, nCol);
		return repositorioUsuarios.save(medico).getId();
	}

	@Override
	public void modificarMedico(String id, String nombre, String apellidos, String email, 
			String telefono, String nCol) throws EntidadNoEncontrada {	
		Medico medico = obtenerMedico(id);
		
		if (nombre != null && !nombre.isBlank())
			medico.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			medico.setApellidos(apellidos);
		if (email != null && ! email.isBlank()) {
			if (!ValidadorEmail.esValido(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			medico.setEmail(email);
		}
		if (telefono != null && !telefono.isBlank())
			medico.setTelefono(telefono);
		if (nCol != null && !nCol.isBlank())
			medico.setNCol(nCol);
		
		repositorioUsuarios.save(medico);
	}

	@Override
	public void eliminarMedico(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		repositorioUsuarios.deleteById(id);
	}
	
	@Override
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		medico.agregarPacientes(lista);
		for (Paciente paciente : lista) {
			if (paciente.getMedicoCabecera() != null) {
				Medico anterior = paciente.getMedicoCabecera();
				eliminarPaciente(anterior.getId(), paciente);
			}
			servicioPacientes.establecerMedico(paciente.getId(), medico);
		}
		repositorioUsuarios.save(medico);
	}

	@Override
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		medico.agregarPaciente(paciente);
		repositorioUsuarios.save(medico);
	}
	
	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		medico.eliminarPacientes(lista);
		for (Paciente paciente : lista) {
			servicioPacientes.establecerMedico(paciente.getId(), null);
		}
		repositorioUsuarios.save(medico);
	}

	@Override
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		medico.eliminarPaciente(paciente);
		repositorioUsuarios.save(medico);
	}
	
	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		medico.agregarAlerta(alerta);
		repositorioUsuarios.save(medico);
	}

	@Override
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		medico.eliminarAlerta(alerta);
		repositorioUsuarios.save(medico);
	}

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
