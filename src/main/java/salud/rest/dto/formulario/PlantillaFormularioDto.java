package salud.rest.dto.formulario;

import java.util.Collection;

import salud.modelo.PlantillaFormulario;
import salud.modelo.encuesta.PreguntaEncuesta;

public class PlantillaFormularioDto {
	
	// Atributos
	
	private String nombre;
	private String descripcion;
	private boolean publico;
	private Collection<PreguntaEncuesta> datos;
	
	// Constructores
	
	public PlantillaFormularioDto() {
		
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

	public Collection<PreguntaEncuesta> getDatos() {
		return datos;
	}

	public void setDatos(Collection<PreguntaEncuesta> datos) {
		this.datos = datos;
	}
	
	public static PlantillaFormularioDto from(PlantillaFormulario formulario) {
		PlantillaFormularioDto formularioDto = new PlantillaFormularioDto();
		formularioDto.setDatos(formulario.getDatos());	
		formularioDto.setNombre(formulario.getNombre());
		formularioDto.setDescripcion(formulario.getDescripcion());
		formularioDto.setPublico(formulario.isPublico());
		
		return formularioDto;
	}
}
