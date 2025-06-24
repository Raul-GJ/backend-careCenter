package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorCampos;

@Service
@Transactional
public class ServicioMedicos implements IServicioMedicos {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioUsuarios servicioUsuarios;
	private IServicioPacientes servicioPacientes;
	
	// Constructores
	
	public ServicioMedicos(RepositorioUsuarios repositorioUsuarios, IServicioUsuarios servicioUsuarios,
			IServicioPacientes servicioPacientes) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioUsuarios = servicioUsuarios;
		this.servicioPacientes = servicioPacientes;
	}
	
	// Métodos
	
	@Override
	public String altaMedico(String nombre, String apellidos, String email, String telefono, 
			LocalDate fechaNacimiento, String sexo, String direccion, String dni, 
			String contrasenya, String nCol, String centroDeSalud) {
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
		if (contrasenya == null || contrasenya.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		if (!ValidadorCampos.validarNcol(nCol)) {
			throw new IllegalArgumentException("El número de colegiado debe ser válido");
		}
		if (repositorioUsuarios.findBynCol(nCol).isPresent()) {
			throw new IllegalArgumentException("Ya existe un usuario con ese número de colegiado");
		}
		if (centroDeSalud == null || centroDeSalud.isEmpty()) {
			throw new IllegalArgumentException("El centro de salud no puede ser nulo o vacío");
		}
		
		Medico medico = new Medico(nombre, apellidos, email, telefono, fechaNacimiento, 
				sexo, direccion, dni, contrasenya, nCol, centroDeSalud);
		return repositorioUsuarios.save(medico).getId();
	}

	@Override
	public void modificarMedico(String id, String nombre, String apellidos, String email, 
			String telefono, LocalDate fechaNacimiento, String sexo, String direccion, String dni,
			String nCol, String centroDeSalud) throws EntidadNoEncontrada {	
		Medico medico = obtenerMedico(id);
		
		if (nombre != null && !nombre.isBlank())
			medico.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			medico.setApellidos(apellidos);
		if (email != null && ! email.isBlank()) {
			if (!ValidadorCampos.validarEmail(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			medico.setEmail(email);
		}
		if (telefono != null && !telefono.isBlank())
			medico.setTelefono(telefono);
		if (fechaNacimiento != null && !fechaNacimiento.isAfter(LocalDate.now()))
			medico.setFechaNacimiento(fechaNacimiento);
		if (sexo != null) {
			if (!sexo.equals("hombre") && !sexo.equals("mujer")) {
				throw new IllegalArgumentException("El sexo no es válido");
			}
			medico.setSexo(sexo);
		}
		if (direccion != null && !direccion.isBlank())
			medico.setDireccion(direccion);
		if (dni != null && !dni.isBlank()) {
			if (!ValidadorCampos.validarDni(dni)) {
				throw new IllegalArgumentException("El dni debe ser válido");
			}
			if (repositorioUsuarios.findByDni(dni).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese dni");
			}
			medico.setDni(dni);
		}
		if (nCol != null && !nCol.isBlank()) {
			if (!ValidadorCampos.validarNcol(nCol)) {
				throw new IllegalArgumentException("El número de colegiado debe ser válido");
			}
			if (repositorioUsuarios.findBynCol(nCol).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese número de colegiado");
			}
			medico.setNCol(nCol);
		}
		if (centroDeSalud != null && !centroDeSalud.isBlank())
			medico.setCentroDeSalud(centroDeSalud);
		
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
		for (Paciente paciente : medico.getPacientes()) {
			if (pacientes.contains(paciente.getId()))
				throw new ConflictException("No puedes agregar pacientes que ya están en tu lista de pacientes");
		}
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
		if (medico.getPacientes().contains(paciente))
			throw new ConflictException("No puedes agregar pacientes que ya están en tu lista de pacientes");
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
	public Medico obtenerMedico(String id) throws EntidadNoEncontrada {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		if (!(usuario instanceof Medico))
			throw new EntidadNoEncontrada(id);
		Medico medico = (Medico) usuario;
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
