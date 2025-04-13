package salud.rest.dto.formulario.tipos;

import salud.modelo.encuesta.ReglaBooleano;

import javax.validation.constraints.NotEmpty;

import salud.modelo.encuesta.PreguntaEncuesta;

public class TipoDatoBooleanoDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'pregunta' no debe estar vacío")
	private String pregunta;
	
	// Constructores
	
	public TipoDatoBooleanoDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaBooleano());
		return tipo;
	}
	
	public static TipoDatoBooleanoDto from(String pregunta) {
		TipoDatoBooleanoDto dto = new TipoDatoBooleanoDto();
		dto.setPregunta(pregunta);
		return dto;
	}
}
