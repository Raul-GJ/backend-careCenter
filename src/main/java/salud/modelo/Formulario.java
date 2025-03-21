package salud.modelo;

import java.time.LocalDateTime;
import java.util.Collection;

import salud.modelo.encuesta.DatoEncuesta;

public class Formulario {
	
	// Atributos
	
	private LocalDateTime fecha;
	private PlantillaFormulario formulario;
	private Collection<DatoEncuesta> datos;
	
	// Constructores
	
	public Formulario(LocalDateTime fecha, PlantillaFormulario formulario, Collection<DatoEncuesta> datos) {
		super();
		this.fecha = fecha;
		this.formulario = formulario;
		this.datos = datos;
	}
	
	// Métodos
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public PlantillaFormulario getFormulario() {
		return formulario;
	}

	public void setFormulario(PlantillaFormulario formulario) {
		this.formulario = formulario;
	}

	public Collection<DatoEncuesta> getDatos() {
		return datos;
	}

	public void setDatos(Collection<DatoEncuesta> datos) {
		this.datos = datos;
	}
}
