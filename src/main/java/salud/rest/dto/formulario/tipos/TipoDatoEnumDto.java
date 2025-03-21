package salud.rest.dto.formulario.tipos;

import java.util.Collection;

import salud.modelo.encuesta.ReglaEnum;
import salud.modelo.encuesta.PreguntaEncuesta;

public class TipoDatoEnumDto {
	
	// Atributos
	
	private String pregunta;
	private Collection<String> values;
	
	// Constructores
	
	public TipoDatoEnumDto() {
		
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Collection<String> getValues() {
		return values;
	}

	public void setValues(Collection<String> values) {
		this.values = values;
	}
	
	public PreguntaEncuesta toPreguntaEncuesta() {
		PreguntaEncuesta tipo = new PreguntaEncuesta(pregunta, new ReglaEnum(values));
		return tipo;
	}

	public static TipoDatoEnumDto from(String pregunta, Collection<String> values) {
		TipoDatoEnumDto dto = new TipoDatoEnumDto();
		dto.setPregunta(pregunta);
		dto.setValues(values);
		return dto;
	}
}
