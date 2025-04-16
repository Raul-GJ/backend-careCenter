package salud.servicio;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Plantilla;
import salud.repositorio.RepositorioEspecialistas;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionAlertas;
import salud.servicio.obtencion.IServicioObtencionEspecialistas;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioEspecialistas implements IServicioEspecialistas {

	// Atributos
	
	private RepositorioEspecialistas repositorioEspecialistas;
	private IServicioObtencionEspecialistas servicioEspecialistas;
	private IServicioPacientes servicioPacientes;
	private IServicioPlantillas servicioPlantillas;
	private IServicioObtencionAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioEspecialistas(RepositorioEspecialistas repositorioEspecialistas,
			IServicioObtencionEspecialistas servicioEspecialistas, IServicioPacientes servicioPacientes,
			IServicioPlantillas servicioPlantillas, IServicioObtencionAlertas servicioAlertas) {
		super();
		this.repositorioEspecialistas = repositorioEspecialistas;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioPacientes = servicioPacientes;
		this.servicioPlantillas = servicioPlantillas;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos
	
	@Override
	public String altaEspecialista(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String especialidad) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("El email no puede ser nulo o vacío");
		}
		if (!ValidadorEmail.esValido(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		if (especialidad == null || especialidad.isEmpty()) {
			throw new IllegalArgumentException("La especialidad no puede ser nula o vacía");
		}
		
		Especialista especialista = new Especialista(nombre, apellido1, apellido2, email, telefono, nCol, especialidad);
		return repositorioEspecialistas.save(especialista).getId();
	}

	@Override
	public void modificarEspecialista(String id, String nombre, String apellido1, String apellido2, 
			String email, String telefono, String nCol, String especialidad) throws EntidadNoEncontrada {
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
		if (!ValidadorEmail.esValido(email)) {
			throw new IllegalArgumentException("El email debe ser válido");
		}
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		if (especialidad == null || especialidad.isEmpty()) {
			throw new IllegalArgumentException("La especialidad no puede ser nula o vacía");
		}
		
		Especialista especialista = obtenerEspecialista(id);
		
		especialista.setNombre(nombre);
		especialista.setApellido1(apellido1);
		especialista.setApellido2(apellido2);
		especialista.setEmail(email);
		especialista.setTelefono(telefono);
		especialista.setNCol(nCol);
		especialista.setEspecialidad(especialidad);
		
		repositorioEspecialistas.save(especialista);
	}
	
	@Override
	public void agregarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		especialista.agregarPacientes(lista);
		for (Paciente paciente : lista) {
			servicioPacientes.agregarEspecialista(paciente.getId(), especialista);
		}
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Paciente> lista = servicioPacientes.obtenerPacientes(pacientes);
		especialista.eliminarPacientes(lista);
		for (Paciente paciente : lista) {
			servicioPacientes.eliminarEspecialista(paciente.getId(), especialista);
		}
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada {
		repositorioEspecialistas.deleteById(id);
	}

	@Override
	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Plantilla> lista = servicioPlantillas.obtenerPlantillas(plantillas);
		especialista.agregarPlantillas(lista);
		repositorioEspecialistas.save(especialista);
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
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarPaciente(paciente);
		servicioPacientes.agregarEspecialista(paciente.getId(), especialista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarPlantilla(plantilla);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		especialista.agregarAlertas(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarAlerta(alerta);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarAlertas(String id, Collection<String> alertas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Alerta> lista = servicioAlertas.obtenerAlertas(alertas);
		especialista.eliminarAlertas(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarPaciente(paciente);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPlantilla(String id, Plantilla plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarPlantilla(plantilla);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarAlerta(String id, Alerta alerta) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarAlerta(alerta);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		return servicioEspecialistas.obtenerEspecialista(id);
	}

	@Override
	public Collection<Especialista> obtenerEspecialistas() {
		return servicioEspecialistas.obtenerEspecialistas();
	}

	@Override
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids) {
		return servicioEspecialistas.obtenerEspecialistas(ids);
	}
}
