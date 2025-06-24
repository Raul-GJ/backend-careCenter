package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Formulario;
import salud.modelo.Plantilla;
import salud.modelo.Seguimiento;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.excepciones.ConflictException;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioSeguimientos implements IServicioSeguimientos {

	// Atributos
	
	private RepositorioSeguimientos repositorioSeguimientos;
	private IServicioPlantillas servicioPlantillas;
	
	// Constructores

	public ServicioSeguimientos(RepositorioSeguimientos repositorioSeguimientos,
			IServicioPlantillas servicioPlantillas) {
		super();
		this.repositorioSeguimientos = repositorioSeguimientos;
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
		if (seguimiento.getFecha().isAfter(LocalDateTime.now()) 
				&& seguimiento.getPlazo().isBefore(LocalDateTime.now()))
			throw new ConflictException("No se puede rellenar un formulario fuera de plazo");
		if (!seguimiento.getFormulario().setRespuestas(respuestas)) {
			throw new IllegalArgumentException("Los datos introducidos son incorrectos");
		}
		repositorioSeguimientos.save(seguimiento);
	}

	@Override
	public Seguimiento obtenerSeguimiento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Seguimiento> optional = repositorioSeguimientos.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Seguimiento seguimiento = optional.get();
		
		return seguimiento;
	}

	@Override
	public Collection<Seguimiento> obtenerSeguimientos() {
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAll().forEach(seguimiento -> seguimientos.add(seguimiento));
		return seguimientos;
	}
	
	@Override
	public Collection<Seguimiento> obtenerSeguimientos(Collection<String> ids) {
		Collection<Seguimiento> seguimientos = new LinkedList<Seguimiento>();
		repositorioSeguimientos.findAllById(ids).forEach(seguimiento -> seguimientos.add(seguimiento));
		return seguimientos;
	}
}
