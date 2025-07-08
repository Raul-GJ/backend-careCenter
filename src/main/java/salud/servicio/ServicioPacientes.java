package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.NotaPaciente;
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
	private IServicioUsuarios servicioUsuarios;
	private IServicioSeguimientos servicioSeguimientos;
	private IServicioNotas servicioNotas;
	
	// Constructores
	
	public ServicioPacientes(RepositorioUsuarios repositorioUsuarios, IServicioUsuarios servicioUsuarios,
			IServicioSeguimientos servicioSeguimientos, IServicioNotas servicioNotas) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioUsuarios = servicioUsuarios;
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioNotas = servicioNotas;
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
		if (!sexo.equals("hombre") && !sexo.equals("mujer")) {
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
		if (sexo != null) {
			if (!sexo.equals("hombre") && !sexo.equals("mujer")) {
				throw new IllegalArgumentException("El sexo no es válido");
			}
			paciente.setSexo(sexo);
		}
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
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		if (!(usuario instanceof Paciente))
			throw new EntidadNoEncontrada(id);
		Paciente paciente = (Paciente) usuario;
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

	@Override
	public void agregarAlergias(String id, Collection<String> alergias) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarAlergias(alergias);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarAlergia(String id, String alergia) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarAlergia(alergia);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarTratamientos(String id, Collection<String> tratamientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarTratamientos(tratamientos);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarTratamiento(String id, String tratamiento) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarTratamiento(tratamiento);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarNotas(String id, Collection<String> notas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		for (String idNota : notas) {
			NotaPaciente nota = servicioNotas.obtenerNota(idNota);
			paciente.agregarNota(nota);
		}
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarNota(String id, String idNota) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		NotaPaciente nota = servicioNotas.obtenerNota(idNota);
		paciente.eliminarNota(nota);
		servicioNotas.eliminarNota(idNota);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarSeguimientosGrupo(String id, String idGrupo) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		System.out.println("Entra");
		for (Seguimiento seguimiento : paciente.getSeguimientos()) {
			System.out.println("Seguimiento: " + seguimiento);
			if (seguimiento.getIdGrupo() != null && seguimiento.getIdGrupo().equals(idGrupo)) {
				System.out.println("Entra 2");
				seguimientos.add(seguimiento);
				servicioSeguimientos.eliminarSeguimiento(seguimiento.getId());
			}
		}
		paciente.eliminarSeguimientos(seguimientos);
		repositorioUsuarios.save(paciente);
	}
}
