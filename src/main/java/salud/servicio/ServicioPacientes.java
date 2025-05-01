package salud.servicio;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionAlertas;
import salud.servicio.obtencion.IServicioObtencionEspecialistas;
import salud.servicio.obtencion.IServicioObtencionPacientes;
import salud.servicio.obtencion.IServicioObtencionSeguimientos;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioPacientes implements IServicioPacientes {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioObtencionPacientes servicioPacientes;
	private IServicioObtencionAlertas servicioAlertas;
	private IServicioObtencionEspecialistas servicioEspecialistas;
	private IServicioObtencionSeguimientos servicioSeguimientos;
	
	// Constructores
	
	public ServicioPacientes(RepositorioUsuarios repositorioUsuarios, 
			IServicioObtencionPacientes servicioPacientes, IServicioObtencionAlertas servicioAlertas,
			IServicioObtencionEspecialistas servicioEspecialistas,
			IServicioObtencionSeguimientos servicioSeguimientos) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioPacientes = servicioPacientes;
		this.servicioAlertas = servicioAlertas;
		this.servicioEspecialistas = servicioEspecialistas;
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
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		paciente.agregarAlertas(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Especialista> lista = servicioEspecialistas.obtenerEspecialistas(especialistas);
		paciente.agregarEspecialistas(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void agregarSeguimientos(String id, Collection<String> seguimientos) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Seguimiento> lista = servicioSeguimientos.obtenerSeguimientos(seguimientos);
		paciente.agregarSeguimientos(lista);
		repositorioUsuarios.save(paciente);
	}
	
	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.agregarAlerta(alerta);
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
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		paciente.eliminarAlertas(lista);
		repositorioUsuarios.save(paciente);
	}

	@Override
	public void eliminarEspecialistas(String id, Collection<String> especialistas) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		Collection<Especialista> lista = servicioEspecialistas.obtenerEspecialistas(especialistas);
		paciente.eliminarEspecialistas(lista);
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
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Paciente paciente = obtenerPaciente(id);
		paciente.eliminarAlerta(alerta);
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
		return servicioPacientes.obtenerPaciente(id);
	}

	@Override
	public Collection<Paciente> obtenerPacientes(Collection<String> ids) {
		return servicioPacientes.obtenerPacientes(ids);
	}

	@Override
	public Collection<Paciente> obtenerPacientes() {
		return servicioPacientes.obtenerPacientes();
	}
}
