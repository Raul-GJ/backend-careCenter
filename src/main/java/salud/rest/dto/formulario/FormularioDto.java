package salud.rest.dto.formulario;

import java.util.List;

import salud.modelo.Formulario;

public class FormularioDto {
	
	// Atributos
	
	private String fecha;
	private String plantilla;
	private List<String> respuestas;
	
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
	
	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public static FormularioDto from(Formulario formulario) {
		FormularioDto formularioDto = new FormularioDto();
		formularioDto.setFecha(formulario.getFecha().toString());
		formularioDto.setRespuestas(formulario.getRespuestas());
		formularioDto.setPlantilla(formulario.getPlantilla().getId());
		
		return formularioDto;
	}
}
