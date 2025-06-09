package salud.rest.dto.formulario.tipos;

import salud.modelo.encuesta.ReglaNumerico;
import salud.modelo.encuesta.PreguntaEncuesta;

public class TipoDatoNumericoDto {
	
	// Atributos
	
	private String pregunta;
	
	// Constructores
	
	public TipoDatoNumericoDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaNumerico());
		return tipo;
	}
	
	public static TipoDatoNumericoDto from(String pregunta) {
		TipoDatoNumericoDto dto = new TipoDatoNumericoDto();
		dto.setPregunta(pregunta);
		return dto;
	}
}
