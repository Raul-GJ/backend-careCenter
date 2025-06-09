package salud.rest.dto.formulario.tipos;

import salud.modelo.encuesta.PreguntaEncuesta;
import salud.modelo.encuesta.ReglaTexto;

public class TipoDatoTextoDto {
	
	// Atributos
	
	private String pregunta;
	
	// Constructores
	
	public TipoDatoTextoDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaTexto());
		return tipo;
	}
	
	public static TipoDatoTextoDto from(String pregunta) {
		TipoDatoTextoDto dto = new TipoDatoTextoDto();
		dto.setPregunta(pregunta);
		return dto;
	}
}
