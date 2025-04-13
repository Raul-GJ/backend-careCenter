package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Formulario;
import salud.modelo.PlantillaFormulario;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioFormulariosPlantilla;
import salud.repositorio.RepositorioSeguimientos;

@Service
@Transactional
public class ServicioSeguimientos implements IServicioSeguimientos {

	// Atributos
	
	private RepositorioSeguimientos repositorioSeguimientos;
	private RepositorioFormulariosPlantilla repositorioFormulariosPlantilla;
	
	// Constructores
	
	@Autowired
	public ServicioSeguimientos(RepositorioSeguimientos repositorioSeguimientos,
			RepositorioFormulariosPlantilla repositorioFormulariosPlantilla) {
		super();
		this.repositorioSeguimientos = repositorioSeguimientos;
		this.repositorioFormulariosPlantilla = repositorioFormulariosPlantilla;
	}
	
	// Métodos
	
	@Override
	public String altaSeguimiento(LocalDateTime fecha, LocalDateTime plazo,  
			String plantilla) throws EntidadNoEncontrada {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Optional<PlantillaFormulario> optional = repositorioFormulariosPlantilla.findById(plantilla);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(plantilla);
		}
		PlantillaFormulario formulario = optional.get();
		
		Seguimiento seguimiento = new Seguimiento(fecha, plazo, new Formulario(fecha, formulario));
		
		return repositorioSeguimientos.save(seguimiento).getId();
	}

	@Override
	public void modificarSeguimiento(String id, LocalDateTime fecha, LocalDateTime plazo,
			String plantilla) throws EntidadNoEncontrada {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Seguimiento seguimiento = obtenerSeguimiento(id);
		
		Optional<PlantillaFormulario> optional = repositorioFormulariosPlantilla.findById(plantilla);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(plantilla);
		}
		PlantillaFormulario formulario = optional.get();
		
		seguimiento.setFecha(fecha);
		seguimiento.setPlazo(plazo);
		seguimiento.getFormulario().setPlantilla(formulario);
		
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
}
