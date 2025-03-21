package salud.rest.dto.formulario;

import java.util.Collection;

import salud.modelo.Formulario;
import salud.modelo.encuesta.DatoEncuesta;

public class FormularioDto {
	
	// Atributos
	
	private String fecha;
	private String plantilla;
	private Collection<DatoEncuesta> datos;
	
	// Constructores
	
	public FormularioDto() {
		
	}
	
	// Métodos
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public Collection<DatoEncuesta> getDatos() {
		return datos;
	}

	public void setDatos(Collection<DatoEncuesta> datos) {
		this.datos = datos;
	}
	
	public static FormularioDto from(Formulario formulario) {
		FormularioDto formularioDto = new FormularioDto();
		formularioDto.setFecha(formulario.getFecha().toString());
		formularioDto.setDatos(formulario.getDatos());
		formularioDto.setPlantilla(formulario.getFormulario().getId());
		
		return formularioDto;
	}
}
