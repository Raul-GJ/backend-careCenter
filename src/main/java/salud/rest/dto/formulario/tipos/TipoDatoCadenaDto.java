package salud.rest.dto.formulario.tipos;

import javax.validation.constraints.NotEmpty;

import salud.modelo.encuesta.PreguntaEncuesta;
import salud.modelo.encuesta.ReglaCadena;

public class TipoDatoCadenaDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'pregunta' no debe estar vacío")
	private String pregunta;
	
	// Constructores
	
	public TipoDatoCadenaDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaCadena());
		return tipo;
	}
	
	public static TipoDatoCadenaDto from(String pregunta) {
		TipoDatoCadenaDto dto = new TipoDatoCadenaDto();
		dto.setPregunta(pregunta);
		return dto;
	}
}
