package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.InfoEstudio;
import salud.modelo.Paciente;
import salud.modelo.PlantillaFormulario;
import salud.modelo.RolEstudio;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioEspecialistas;

@Service
@Transactional
public class ServicioEspecialistas implements IServicioEspecialistas {

	// Atributos
	
	private RepositorioEspecialistas repositorioEspecialistas;
	private IServicioPacientes servicioPacientes;
	private IServicioEstudios servicioEstudios;
	private IServicioFormulariosPlantilla servicioPlantillas;
	private IServicioConsultas servicioConsultas;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioEspecialistas(RepositorioEspecialistas repositorioEspecialistas,
			IServicioPacientes servicioPacientes, IServicioEstudios servicioEstudios,
			IServicioFormulariosPlantilla servicioPlantillas, IServicioConsultas servicioConsultas,
			IServicioAlertas servicioAlertas) {
		super();
		this.repositorioEspecialistas = repositorioEspecialistas;
		this.servicioPacientes = servicioPacientes;
		this.servicioEstudios = servicioEstudios;
		this.servicioPlantillas = servicioPlantillas;
		this.servicioConsultas = servicioConsultas;
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
	public Especialista obtenerEspecialista(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Especialista> optional = repositorioEspecialistas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Especialista especialista = optional.get();
		return especialista;
	}

	@Override
	public Collection<Especialista> obtenerEspecialistas() {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		repositorioEspecialistas.findAll().forEach(e -> especialistas.add(e));
		return especialistas;
	}
	
	@Override
	public Collection<Especialista> obtenerEspecialistas(Collection<String> ids) {
		Collection<Especialista> especialistas = new LinkedList<Especialista>();
		repositorioEspecialistas.findAllById(ids).forEach(e -> especialistas.add(e));
		return especialistas;
	}

	@Override
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		
		Collection<Consulta> consultas = especialista.getConsultas();
		for (Consulta consulta : consultas) {
			servicioConsultas.eliminarConsulta(consulta.getId());
		}
		
		Collection<InfoEstudio> estudios = especialista.getEstudios();
		for (InfoEstudio infoEstudio : estudios) {
			if (infoEstudio.getRol().equals(RolEstudio.CREADOR))
				servicioEstudios.eliminarEstudio(infoEstudio.getEstudio().getId());
		}
		
		List<Paciente> pacientes = especialista.getPacientes();
		for (Paciente paciente : pacientes) {
			servicioPacientes.eliminarEspecialista(paciente.getId(), especialista);
		}
		
		Collection<PlantillaFormulario> plantillas = especialista.getPlantillas();
		for (PlantillaFormulario p : plantillas) {
			servicioPlantillas.eliminarFormulario(p.getId());
		}
	
		repositorioEspecialistas.deleteById(id);
	}
	
	public void agregarEstudios(String id, Collection<String> estudios, RolEstudio rol) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Estudio> listaTmp = servicioEstudios.obtenerEstudios(estudios);
		Collection<InfoEstudio> lista = new LinkedList<InfoEstudio>();
		listaTmp.forEach(e -> lista.add(new InfoEstudio(rol, e)));
		especialista.agregarEstudios(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarEstudiosCreador(String id, Collection<String> estudios) throws EntidadNoEncontrada {
		agregarEstudios(id, estudios, RolEstudio.CREADOR);
	}
	
	@Override
	public void agregarEstudiosEditor(String id, Collection<String> estudios) throws EntidadNoEncontrada {
		agregarEstudios(id, estudios, RolEstudio.EDITOR);
	}
	
	@Override
	public void agregarEstudiosObservador(String id, Collection<String> estudios) throws EntidadNoEncontrada {
		agregarEstudios(id, estudios, RolEstudio.OBSERVADOR);
	}

	@Override
	public void eliminarEstudios(String id, Collection<String> estudios) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Estudio> lista = servicioEstudios.obtenerEstudios(estudios);
		especialista.eliminarEstudios(lista);
		for (Estudio estudio : lista) {
			servicioEstudios.eliminarEspecialista(estudio.getId(), especialista);
		}
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<PlantillaFormulario> lista = servicioPlantillas.obtenerFormularios(plantillas);
		especialista.agregarPlantillas(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<PlantillaFormulario> lista = servicioPlantillas.obtenerFormularios(plantillas);
		especialista.eliminarPlantillas(lista);
		for (PlantillaFormulario plantillaFormulario : lista) {
			if (!plantillaFormulario.isPublico())
				servicioPlantillas.eliminarFormulario(id);
		}
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Consulta> lista = servicioConsultas.obtenerConsultas(consultas);
		especialista.agregarConsultas(lista);
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
	public void agregarEstudio(String id, Estudio estudio, RolEstudio rol) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarEstudio(new InfoEstudio(rol, estudio));
		//servicioEstudios.agregarEspecialista(estudio.getId(), especialista, rol);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarPlantilla(String id, PlantillaFormulario plantilla) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarPlantilla(plantilla);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarConsulta(String id, Consulta consulta) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.agregarConsulta(consulta);
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
	public void eliminarEstudio(String id, Estudio estudio) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		especialista.eliminarEstudio(estudio);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPlantilla(String id, PlantillaFormulario plantilla) throws EntidadNoEncontrada {
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
}
