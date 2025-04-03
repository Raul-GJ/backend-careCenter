package salud.servicio;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.Paciente;
import salud.modelo.PlantillaFormulario;
import salud.modelo.RolEstudio;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioConsultas;
import salud.repositorio.RepositorioEspecialistas;
import salud.repositorio.RepositorioEstudios;
import salud.repositorio.RepositorioFormulariosPlantilla;
import salud.repositorio.RepositorioPacientes;
import salud.rest.dto.usuario.EspecialistaDto;

@Service
@Transactional
public class ServicioEspecialistas implements IServicioEspecialistas {

	// Atributos
	
	private RepositorioEspecialistas repositorioEspecialistas;
	private RepositorioPacientes repositorioPacientes;
	private RepositorioEstudios repositorioEstudios;
	private RepositorioFormulariosPlantilla repositorioPlantillas;
	private RepositorioConsultas repositorioConsultas;
	
	// Constructores
	
	public ServicioEspecialistas(RepositorioEspecialistas repositorioEspecialistas,
			RepositorioPacientes repositorioPacientes, RepositorioEstudios repositorioEstudios,
			RepositorioFormulariosPlantilla repositorioPlantillas, RepositorioConsultas repositorioConsultas) {
		super();
		this.repositorioEspecialistas = repositorioEspecialistas;
		this.repositorioPacientes = repositorioPacientes;
		this.repositorioEstudios = repositorioEstudios;
		this.repositorioPlantillas = repositorioPlantillas;
		this.repositorioConsultas = repositorioConsultas;
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
		Collection<Paciente> lista = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(pacientes).forEach(p -> lista.add(p));
		especialista.agregarPacientes(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPacientes(String id, Collection<String> pacientes) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Paciente> lista = new LinkedList<Paciente>();
		repositorioPacientes.findAllById(pacientes).forEach(p -> lista.add(p));
		especialista.eliminarPacientes(lista);
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
	public Collection<EspecialistaDto> obtenerEspecialistas() {
		Collection<EspecialistaDto> especialistas = new LinkedList<EspecialistaDto>();
		repositorioEspecialistas.findAll().forEach(e -> especialistas.add(EspecialistaDto.from(e)));
		return especialistas;
	}
	
	@Override
	public Collection<EspecialistaDto> obtenerEspecialistas(Collection<String> ids) {
		Collection<EspecialistaDto> especialistas = new LinkedList<EspecialistaDto>();
		repositorioEspecialistas.findAllById(ids).forEach(e -> especialistas.add(EspecialistaDto.from(e)));
		return especialistas;
	}

	@Override
	public void eliminarEspecialista(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		repositorioEspecialistas.deleteById(id);
	}
	
	public void agregarEstudios(String id, Collection<String> estudios, RolEstudio rol) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Estudio> lista = new LinkedList<Estudio>();
		repositorioEstudios.findAllById(estudios).forEach(e -> lista.add(e));
		Map<RolEstudio, Collection<Estudio>> mapa = new HashMap<RolEstudio, Collection<Estudio>>();
		mapa.put(rol, lista);
		especialista.agregarEstudios(mapa);
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
		Collection<Estudio> lista = new LinkedList<Estudio>();
		repositorioEstudios.findAllById(estudios).forEach(e -> lista.add(e));
		especialista.eliminarEstudios(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<PlantillaFormulario> lista = new LinkedList<PlantillaFormulario>();
		repositorioPlantillas.findAllById(plantillas).forEach(p -> lista.add(p));
		especialista.agregarPlantillas(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void eliminarPlantillas(String id, Collection<String> plantillas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<PlantillaFormulario> lista = new LinkedList<PlantillaFormulario>();
		repositorioPlantillas.findAllById(plantillas).forEach(p -> lista.add(p));
		especialista.eliminarPlantillas(lista);
		repositorioEspecialistas.save(especialista);
	}

	@Override
	public void agregarConsultas(String id, Collection<String> consultas) throws EntidadNoEncontrada {
		Especialista especialista = obtenerEspecialista(id);
		Collection<Consulta> lista = new LinkedList<Consulta>();
		repositorioConsultas.findAllById(consultas).forEach(c -> lista.add(c));
		especialista.agregarConsultas(lista);
		repositorioEspecialistas.save(especialista);
	}
}
