package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Formulario;
import salud.modelo.Plantilla;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionSeguimientos;

@Service
@Transactional
public class ServicioSeguimientos implements IServicioSeguimientos {

	// Atributos
	
	private RepositorioSeguimientos repositorioSeguimientos;
	private IServicioObtencionSeguimientos servicioSeguimientos;
	private IServicioPlantillas servicioPlantillas;
	
	// Constructores

	public ServicioSeguimientos(RepositorioSeguimientos repositorioSeguimientos,
			IServicioObtencionSeguimientos servicioSeguimientos, IServicioPlantillas servicioPlantillas) {
		super();
		this.repositorioSeguimientos = repositorioSeguimientos;
		this.servicioSeguimientos = servicioSeguimientos;
		this.servicioPlantillas = servicioPlantillas;
	}
	
	// Métodos
	
	@Override
	public String altaSeguimiento(LocalDateTime fecha, LocalDateTime plazo,  
			String plantilla, String motivo) throws EntidadNoEncontrada {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		if (plazo == null) {
			throw new IllegalArgumentException("El plazo no puede ser nulo");
		}
		if (plazo.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("El plazo no puede ser anterior al dia de hoy");
		}
		if (plazo.isBefore(fecha)) {
			throw new IllegalArgumentException("El plazo no puede ser anterior a la fecha");
		}
		
		Plantilla formulario = servicioPlantillas.obtenerPlantilla(plantilla);
		
		Seguimiento seguimiento = new Seguimiento(fecha, plazo, new Formulario(fecha, formulario), motivo);
		
		return repositorioSeguimientos.save(seguimiento).getId();
	}

	@Override
	public void modificarSeguimiento(String id, LocalDateTime fecha, LocalDateTime plazo,
			String plantilla, String motivo) throws EntidadNoEncontrada {
		Seguimiento seguimiento = obtenerSeguimiento(id);
		
		if (plantilla != null) {
			Plantilla formulario = servicioPlantillas.obtenerPlantilla(plantilla);
			seguimiento.getFormulario().setPlantilla(formulario);
		}
		
		if (fecha != null && fecha.isAfter(LocalDateTime.now()))
			seguimiento.setFecha(fecha);
		if (plazo != null && plazo.isAfter(fecha))
			seguimiento.setPlazo(plazo);
		if (motivo != null && !motivo.isBlank())
			seguimiento.setMotivo(motivo);
		
		repositorioSeguimientos.save(seguimiento);

	}
	
	@Override
	public void eliminarSeguimiento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioSeguimientos.deleteById(id);
	}

	@Override
	public void rellenarFormulario(String id, List<String> respuestas) throws EntidadNoEncontrada {
		Seguimiento seguimiento = obtenerSeguimiento(id);
		if (!seguimiento.getFormulario().setRespuestas(respuestas)) {
			throw new IllegalArgumentException("Los datos introducidos son incorrectos");
		}
		repositorioSeguimientos.save(seguimiento);
	}

	@Override
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada {
		return servicioSeguimientos.obtenerSeguimiento(id);
	}

	@Override
	public Collection<Seguimiento> obtenerSeguimientos() {
		return servicioSeguimientos.obtenerSeguimientos();
	}

	@Override
	public Collection<Seguimiento> obtenerSeguimientos(Collection<String> ids) {
		return servicioSeguimientos.obtenerSeguimientos(ids);
	}
}
