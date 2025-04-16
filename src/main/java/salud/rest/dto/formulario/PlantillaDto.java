package salud.rest.dto.formulario;

import java.util.Collection;

import salud.modelo.Plantilla;
import salud.modelo.encuesta.PreguntaEncuesta;

public class PlantillaDto {
	
	// Atributos
	
	private String nombre;
	private String descripcion;
	private boolean publico;
	private Collection<PreguntaEncuesta> preguntas;
	
	// Constructores
	
	public PlantillaDto() {
		
	}
	
	// Métodos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public Collection<PreguntaEncuesta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Collection<PreguntaEncuesta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public static PlantillaDto from(Plantilla formulario) {
		PlantillaDto formularioDto = new PlantillaDto();
		formularioDto.setPreguntas(formulario.getPreguntas());	
		formularioDto.setNombre(formulario.getNombre());
		formularioDto.setDescripcion(formulario.getDescripcion());
		formularioDto.setPublico(formulario.isPublico());
		
		return formularioDto;
	}
}
