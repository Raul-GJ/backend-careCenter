package salud.rest.dto.formulario.tipos;

import salud.modelo.encuesta.ReglaRango;
import salud.modelo.encuesta.PreguntaEncuesta;

public class TipoDatoRangoDto {
	
	// Atributos
	
	private String pregunta;
	private int minValue;
	private int maxValue;
	
	// Constructores
	
	public TipoDatoRangoDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaRango(minValue, maxValue));
		return tipo;
	}

	public static TipoDatoRangoDto from(String pregunta, int minValue, int maxValue) {
		TipoDatoRangoDto dto = new TipoDatoRangoDto();
		dto.setPregunta(pregunta);
		dto.setMinValue(minValue);
		dto.setMaxValue(maxValue);
		return dto;
	}
}
