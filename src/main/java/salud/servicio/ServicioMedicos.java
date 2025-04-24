package salud.servicio;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Medico;
import salud.modelo.Paciente;
import salud.repositorio.RepositorioUsuarios;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionMedicos;
import salud.utils.ValidadorEmail;

@Service
@Transactional
public class ServicioMedicos implements IServicioMedicos {

	// Atributos
	
	private RepositorioUsuarios repositorioUsuarios;
	private IServicioObtencionMedicos servicioMedicos;
	
	// Constructores
	
	public ServicioMedicos(RepositorioUsuarios repositorioUsuarios, 
			IServicioObtencionMedicos servicioMedicos) {
		super();
		this.repositorioUsuarios = repositorioUsuarios;
		this.servicioMedicos = servicioMedicos;
	}
	
	// Métodos
	
	@Override
	public String altaMedico(String nombre, String apellidos, String email, 
			String telefono, String nCol, String atributoTemporal) {
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
		if (nCol == null || nCol.isEmpty()) {
			throw new IllegalArgumentException("El nCol no puede ser nulo o vacío");
		}
		if (atributoTemporal == null || atributoTemporal.isEmpty()) {
			throw new IllegalArgumentException("El atributo temporal no puede ser nulo o vacío");
		}
		
		Medico medico = new Medico(nombre, apellidos, email, telefono, nCol, atributoTemporal);
		return repositorioUsuarios.save(medico).getId();
	}

	@Override
	public void modificarMedico(String id, String nombre, String apellidos, String email, 
			String telefono, String nCol, String atributoTemporal) throws EntidadNoEncontrada {	
		Medico medico = obtenerMedico(id);
		
		if (nombre != null && !nombre.isBlank())
			medico.setNombre(nombre);
		if (apellidos != null && !apellidos.isBlank())
			medico.setApellidos(apellidos);
		if (email != null && ! email.isBlank())
			medico.setEmail(email);
		if (telefono != null && !telefono.isBlank())
			medico.setTelefono(telefono);
		if (nCol != null && !nCol.isBlank())
			medico.setNCol(nCol);
		if (atributoTemporal != null && !atributoTemporal.isBlank())
			medico.setAtributoTemporal(atributoTemporal);
		
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
	public void agregarPaciente(String id, Paciente paciente) throws EntidadNoEncontrada {
		Medico medico = obtenerMedico(id);
		medico.agregarPaciente(paciente);
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
		return servicioMedicos.obtenerMedico(id);
	}

	@Override
	public Collection<Medico> obtenerMedicos() {
		return servicioMedicos.obtenerMedicos();
	}

	@Override
	public Collection<Medico> obtenerMedicos(Collection<String> ids) {
		return servicioMedicos.obtenerMedicos(ids);
	}
}
