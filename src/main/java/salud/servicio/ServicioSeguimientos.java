package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.PlantillaFormulario;
import salud.modelo.Seguimiento;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioSeguimientos;
import salud.rest.dto.seguimiento.SeguimientoDto;

@Service
@Transactional
public class ServicioSeguimientos implements IServicioSeguimientos {

	// Atributos
	
	private RepositorioSeguimientos repositorioSeguimientos;
	
	// Constructores
	
	@Autowired
	public ServicioSeguimientos(RepositorioSeguimientos repositorioSeguimientos) {
		super();
		this.repositorioSeguimientos = repositorioSeguimientos;
	}
	
	// Métodos
	
	@Override
	public String altaSeguimiento(LocalDateTime fecha, LocalDateTime plazo,  
			PlantillaFormulario formulario) {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Seguimiento seguimiento = new Seguimiento(fecha, plazo, formulario);
		
		return repositorioSeguimientos.save(seguimiento).getId();
	}

	@Override
	public void modificarSeguimiento(String id, LocalDateTime fecha, LocalDateTime plazo,
			PlantillaFormulario formulario) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("La fecha no puede ser anterior al dia de hoy");
		}
		
		Optional<Seguimiento> optional = repositorioSeguimientos.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Seguimiento seguimiento = optional.get();
		
		seguimiento.setFecha(fecha);
		seguimiento.setPlazo(plazo);
		seguimiento.setFormulario(formulario);
		
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
	public Collection<SeguimientoDto> obtenerSeguimientos() {
		Collection<SeguimientoDto> seguimientos = new LinkedList<SeguimientoDto>();
		repositorioSeguimientos.findAll().forEach(seguimiento -> seguimientos.add(SeguimientoDto.from(seguimiento)));
		return seguimientos;
	}

	@Override
	public Page<SeguimientoDto> obtenerSeguimientosPaginado(Pageable pageable) {
		return repositorioSeguimientos.findAll(pageable).map(seguimiento -> {
			SeguimientoDto dto = SeguimientoDto.from(seguimiento);
			return dto;
		});
	}

	@Override
	public void eliminarSeguimiento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioSeguimientos.deleteById(id);
	}

}
