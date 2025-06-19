package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Plantilla;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioEspecialistas implements IServicioEspecialistas {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioPacientes servicioPacientes;
	private IServicioPlantillas servicioPlantillas;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioEspecialistas(RepositorioUsuarios repositorioUsuarios,
			IServicioPacientes servicioPacientes, IServicioPlantillas servicioPlantillas, 
			IServicioAlertas servicioAlertas) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioPacientes = servicioPacientes;
		this.servicioPlantillas = servicioPlantillas;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos
	
	@Override
	public String altaEspecialista(String nombre, String apellidos, String email, 
			String telefono, String contrasenya, String nCol, String especialidad) {
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
		if (especialidad == null || especialidad.isEmpty()) {
			throw new IllegalArgumentException("La especialidad no puede ser nula o vacía");
		}
		
		Especialista especialista = new Especialista(nombre, apellidos, email, telefono, contrasenya,
				nCol, especialidad);
		return repositorioUsuarios.save(especialista).getId();
	}

	@Override
	public void modificarEspecialista(String id, String nombre, String apellidos, 
			String email, String telefono, String nCol, String especialidad) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		
		if (nombre != null && !nombre.isBlank())
			especialista.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			especialista.setApellidos(apellidos);
		if (email != null && !email.isBlank()) {
			if (!ValidadorEmail.esValido(email)) {
				throw new IllegalArgumentException("El email debe ser válido");
			}
			if (repositorioUsuarios.findByEmail(email).isPresent()) {
				throw new IllegalArgumentException("Ya existe un usuario con ese email");
			}
			especialista.setEmail(email);
		}
			
		if (telefono != null && !telefono.isBlank())
			especialista.setTelefono(telefono);
		if (nCol != null && !nCol.isBlank())
			especialista.setNCol(nCol);
		if (especialidad != null && !especialidad.isBlank())
			especialista.setEspecialidad(especialidad);
		
		repositorioUsuarios.save(especialista);
	}
	
	@Override
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
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
		especialista.agregarPaciente(paciente);
		servicioPacientes.agregarEspecialista(paciente.getId(), especialista);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void agregarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarPlantilla(plantilla);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		especialista.agregarAlertas(lista);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarAlerta(alerta);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		especialista.eliminarAlertas(lista);
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
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarAlerta(alerta);
		repositorioUsuarios.save(especialista);
	}

	@Override
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Usuario> optional = repositorioUsuarios.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		if (!optional.get().getTipo().equals(TipoUsuario.ESPECIALISTA)) {
			throw new IllegalArgumentException("El usuario con id " + id + " no es un especialista");
		}
		Especialista especialista = (Especialista) optional.get();
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
