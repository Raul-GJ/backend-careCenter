package salud.servicio;

import java.time.LocalDate;
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
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorCampos;

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
			LocalDate fechaNacimiento, String sexo, String direccion, String dni, String nss, 
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
		if (!ValidadorCampos.validarEmail(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (repositorioUsuarios.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Ya existe un usuario con ese email");
		}
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
		}
		if (fechaNacimiento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("La fecha de nacimiento no puede estar en el futuro");
		}
		if (sexo == null || sexo.isEmpty()) {
			throw new IllegalArgumentException("El sexo no puede ser nulo o vacío");
		}
		if (!(sexo == "hombre" || sexo == "mujer")) {
			throw new IllegalArgumentException("El sexo no es valido");
		}
		if (direccion == null || direccion.isEmpty()) {
			throw new IllegalArgumentException("La dirección no puede ser nula o vacía");
		}
		if (dni == null || dni.isEmpty()) {
			throw new IllegalArgumentException("El dni no puede ser nulo o vacío");
		}
		if (!ValidadorCampos.validarDni(dni)) {
			throw new IllegalArgumentException("El dni debe ser válido");
		}
		if (repositorioUsuarios.findByDni(dni).isPresent()) {
			throw new IllegalArgumentException("Ya existe un usuario con ese DNI");
		}
		if (nss == null || nss.isEmpty()) {
			throw new IllegalArgumentException("El número de la seguridad social no puede ser nulo o vacío");
		}
		if (!ValidadorCampos.validarNss(nss)) {
			throw new IllegalArgumentException("El número de la seguridad social debe ser válido");
		}
		if (repositorioUsuarios.findByNss(nss).isPresent()) {
			throw new IllegalArgumentException("Ya existe un usuario con ese número de la seguridad social");
		}
		if (contrasenya == null || contrasenya.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		
		Paciente paciente = new Paciente(nombre, apellidos, email, telefono, fechaNacimiento,
				sexo, direccion, dni, nss, contrasenya);
		
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
			String email, String telefono, LocalDate fechaNacimiento, String sexo, String direccion, 
			String dni, String nss) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		
		if (nombre != null && !nombre.isBlank())
			paciente.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			paciente.setApellidos(apellidos);
		if (email != null && !email.isBlank()) {
			if (!ValidadorCampos.validarEmail(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			paciente.setEmail(email);
		}
		if (telefono != null && !telefono.isBlank())
			paciente.setTelefono(telefono);
		if (fechaNacimiento != null && !fechaNacimiento.isAfter(LocalDate.now()))
			paciente.setFechaNacimiento(fechaNacimiento);
		if (sexo != null && (sexo.toLowerCase() == "hombre" || sexo.toLowerCase() == "mujer"))
			paciente.setSexo(sexo);
		if (direccion != null && !direccion.isBlank())
			paciente.setDireccion(direccion);
		if (dni != null && !dni.isBlank()) {
			if (!ValidadorCampos.validarDni(dni)) {
				throw new IllegalArgumentException("El dni debe ser válido");
			}
			if (repositorioUsuarios.findByDni(dni).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese dni");
			}
			paciente.setDni(dni);
		}
		if (nss != null && !nss.isBlank()) {
			if (!ValidadorCampos.validarNss(nss)) {
				throw new IllegalArgumentException("El número de la seguridad social debe ser válido");
			}
			if (repositorioUsuarios.findByNss(nss).isPresent()) {
				throw new IllegalArgumentException(
						"Ya existe un usuario con ese número de la seguridad social");
			}
			paciente.setNss(nss);
		}
		
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
		for (Seguimiento seguimiento : paciente.getSeguimientos()) {
			if (seguimientos.contains(seguimiento.getId()))
				throw new ConflictException("No puedes agregar seguimientos que ya están en tu lista de seguimientos");
		}
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.agregarSeguimientos(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarEspecialista(String id, Especialista especialista) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		if (paciente.getEspecialistas().contains(especialista))
			throw new ConflictException("No puedes agregar especialistas que ya están en tu lista de especialistas");
		paciente.agregarEspecialista(especialista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarSeguimiento(String id, Seguimiento seguimiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		if (paciente.getSeguimientos().contains(seguimiento))
			throw new ConflictException("No puedes agregar seguimientos que ya están en tu lista de seguimientos");
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
