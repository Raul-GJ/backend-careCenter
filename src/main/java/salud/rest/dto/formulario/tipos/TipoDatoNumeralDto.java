package salud.rest.dto.formulario.tipos;

import salud.modelo.encuesta.ReglaNumeral;

import javax.validation.constraints.NotEmpty;

import salud.modelo.encuesta.PreguntaEncuesta;

public class TipoDatoNumeralDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'pregunta' no debe estar vacío")
	private String pregunta;
	
	// Constructores
	
	public TipoDatoNumeralDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaNumeral());
		return tipo;
	}
	
	public static TipoDatoNumeralDto from(String pregunta) {
		TipoDatoNumeralDto dto = new TipoDatoNumeralDto();
		dto.setPregunta(pregunta);
		return dto;
	}
}
