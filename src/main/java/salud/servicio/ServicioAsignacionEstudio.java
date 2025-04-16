package salud.servicio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.AsignacionEstudio;
import salud.modelo.Especialista;
import salud.modelo.Estudio;
import salud.modelo.RolEstudio;
import salud.repositorio.RepositorioAsignacionEstudio;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionEspecialistas;
import salud.servicio.obtencion.IServicioObtencionEstudios;

@Service
@Transactional
public class ServicioAsignacionEstudio implements IservicioAsignacionEstudio {
	
	// Atributos
	
	private RepositorioAsignacionEstudio repositorioAsignacion;
	private IServicioObtencionEspecialistas servicioEspecialistas;
	private IServicioObtencionEstudios servicioEstudios;
	
	
	// Constructores
	
	public ServicioAsignacionEstudio(RepositorioAsignacionEstudio repositorioAsignacion,
			IServicioObtencionEspecialistas servicioEspecialistas, IServicioObtencionEstudios servicioEstudios) {
		super();
		this.repositorioAsignacion = repositorioAsignacion;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioEstudios = servicioEstudios;
	}
	
	// Métodos
	
	@Override
	public String agregarAsignacion(String idEspecialista, String idEstudio, String rolStr)
		throws EntidadNoEncontrada {
		if (idEspecialista == null || idEspecialista.isEmpty()) {
			throw new IllegalArgumentException("El especialista no puede ser nulo o vacío");
		}
		if (idEstudio == null || idEstudio.isEmpty()) {
			throw new IllegalArgumentException("El estudio no puede ser nulo o vacío");
		}
		if (rolStr == null || rolStr.isEmpty()) {
			throw new IllegalArgumentException("El rol no puede ser nulo o vacío");
		}
		
		Especialista especialista = servicioEspecialistas.obtenerEspecialista(idEspecialista);
		Estudio estudio = servicioEstudios.obtenerEstudio(idEstudio);
		RolEstudio rol;
		try {
			rol = RolEstudio.valueOf(rolStr);
		} catch (Exception e) {
			throw new IllegalArgumentException("El rol que has introducido no existe");
		}
		
		AsignacionEstudio asignacion = new AsignacionEstudio(especialista, estudio, rol);
		return repositorioAsignacion.save(asignacion).getId();
	}
	
	@Override
	public void modificarAsignacion(String id, String rol) throws EntidadNoEncontrada {
		AsignacionEstudio asignacion = obtenerAsignacion(id);
		asignacion.setRol(RolEstudio.valueOf(rol));
		repositorioAsignacion.save(asignacion);
	}

	@Override
	public void eliminarAsignacion(String id) throws EntidadNoEncontrada {
		obtenerAsignacion(id); // Para comprobar que existe
		repositorioAsignacion.deleteById(id);
	}

	@Override
	public Collection<AsignacionEstudio> obtenerEspecialistas(String id) throws EntidadNoEncontrada {
		Collection<AsignacionEstudio> lista = new LinkedList<AsignacionEstudio>();
		repositorioAsignacion.findByEstudio_Id(id).forEach(ae -> lista.add(ae));
		return lista;
	}

	@Override
	public Collection<AsignacionEstudio> obtenerEstudios(String id) throws EntidadNoEncontrada {
		Collection<AsignacionEstudio> lista = new LinkedList<AsignacionEstudio>();
		repositorioAsignacion.findByEspecialista_Id(id).forEach(ae -> lista.add(ae));
		return lista;
	}

	@Override
	public AsignacionEstudio obtenerAsignacion(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<AsignacionEstudio> optional = repositorioAsignacion.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		AsignacionEstudio asignacion = optional.get();
		return asignacion;
	}
}
