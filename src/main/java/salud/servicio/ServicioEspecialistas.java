package salud.servicio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Plantilla;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorCampos;

@Service
@Transactional
public class ServicioEspecialistas implements IServicioEspecialistas {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioUsuarios servicioUsuarios;
	private IServicioPacientes servicioPacientes;
	private IServicioPlantillas servicioPlantillas;
	
	// Constructores
	
	public ServicioEspecialistas(RepositorioUsuarios repositorioUsuarios, IServicioUsuarios servicioUsuarios,
			IServicioPacientes servicioPacientes, IServicioPlantillas servicioPlantillas) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioUsuarios = servicioUsuarios;
		this.servicioPacientes = servicioPacientes;
		this.servicioPlantillas = servicioPlantillas;
	}
	
	// Métodos
	
	@Override
	public String altaEspecialista(String nombre, String apellidos, String email, 
			String telefono, LocalDate fechaNacimiento, String sexo, String direccion, String dni,
			String contrasenya, String nCol, String centroDeSalud, String especialidad) {
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
		if (especialidad == null || especialidad.isEmpty()) {
			throw new IllegalArgumentException("La especialidad no puede ser nula o vacía");
		}
		
		Especialista especialista = new Especialista(nombre, apellidos, email, telefono, fechaNacimiento, 
				sexo, direccion, dni, contrasenya, nCol, centroDeSalud, especialidad);
		return repositorioUsuarios.save(especialista).getId();
	}

	@Override
	public void modificarEspecialista(String id, String nombre, String apellidos, 
			String email, String telefono, LocalDate fechaNacimiento, String sexo, String direccion, 
			String dni, String nCol, String centroDeSalud, String especialidad) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		
		if (nombre != null && !nombre.isBlank())
			especialista.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			especialista.setApellidos(apellidos);
		if (email != null && !email.isBlank()) {
			if (!ValidadorCampos.validarEmail(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			especialista.setEmail(email);
		}
			
		if (telefono != null && !telefono.isBlank())
			especialista.setTelefono(telefono);
		if (fechaNacimiento != null && !fechaNacimiento.isAfter(LocalDate.now()))
			especialista.setFechaNacimiento(fechaNacimiento);
		if (sexo != null) {
			if (!sexo.equals("hombre") && !sexo.equals("mujer")) {
				throw new IllegalArgumentException("El sexo no es válido");
			}
			especialista.setSexo(sexo);
		}
		if (direccion != null && !direccion.isBlank())
			especialista.setDireccion(direccion);
		if (dni != null && !dni.isBlank()) {
			if (!ValidadorCampos.validarDni(dni)) {
				throw new IllegalArgumentException("El dni debe ser válido");
			}
			if (repositorioUsuarios.findByDni(dni).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese dni");
			}
			especialista.setDni(dni);
		}
		if (nCol != null && !nCol.isBlank()) {
			if (!ValidadorCampos.validarNcol(nCol)) {
				throw new IllegalArgumentException("El número de colegiado debe ser válido");
			}
			if (repositorioUsuarios.findBynCol(nCol).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese número de colegiado");
			}
			especialista.setNCol(nCol);
		}
		if (centroDeSalud != null && !centroDeSalud.isBlank())
			especialista.setCentroDeSalud(centroDeSalud);
		if (especialidad != null && !especialidad.isBlank())
			especialista.setEspecialidad(especialidad);
		
		repositorioUsuarios.save(especialista);
	}
	
	@Override
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		for (Paciente paciente : especialista.getPacientes()) {
			if (pacientes.contains(paciente.getId()))
				throw new ConflictException("No puedes agregar pacientes que ya están en tu lista de pacientes");
		}
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		especialista.agregarPacientes(lista);
		for (Paciente paciente : lista) {
			servicioPacientes.agregarEspecialista(paciente.getId(), especialista);
		}
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		especialista.eliminarPacientes(lista);
		for (Paciente paciente : lista) {
			servicioPacientes.eliminarEspecialista(paciente.getId(), especialista);
		}
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada {
		repositorioUsuarios.deleteById(id);
	}

	@Override
	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		for (Plantilla plantilla : especialista.getPlantillas()) {
			if (plantillas.contains(plantilla.getId()))
				throw new ConflictException("No puedes agregar plantillas que ya están en tu lista de plantillas");
		}
		Collection<Plantilla> lista = servicioPlantillas.obtenerPlantillas(plantillas);
		especialista.agregarPlantillas(lista);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Plantilla> lista = servicioPlantillas.obtenerPlantillas(plantillas);
		especialista.eliminarPlantillas(lista);
		for (Plantilla plantillaPlantilla : lista) {
			if (!plantillaPlantilla.isPublico())
				servicioPlantillas.eliminarPlantilla(id);
		}
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		if (especialista.getPacientes().contains(paciente))
			throw new ConflictException("No puedes agregar pacientes que ya están en tu lista de pacientes");
		especialista.agregarPaciente(paciente);
		servicioPacientes.agregarEspecialista(paciente.getId(), especialista);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void agregarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		if (especialista.getPlantillas().contains(plantilla))
			throw new ConflictException("No puedes agregar plantillas que ya están en tu lista de plantillas");
		especialista.agregarPlantilla(plantilla);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarPaciente(paciente);
		servicioPacientes.eliminarEspecialista(paciente.getId(), especialista);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarPlantilla(plantilla);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		if (!(usuario instanceof Especialista))
			throw new EntidadNoEncontrada(id);
		Especialista especialista = (Especialista) usuario;
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
