package salud.modelo.encuesta;

import java.util.Objects;

public class DatoEncuesta {
	
	// Atributos
	
	private String respuesta;
	private PreguntaEncuesta pregunta;
	
	// Constructores
	
	public DatoEncuesta(String respuesta, PreguntaEncuesta pregunta) {
		super();
		this.respuesta = respuesta;
		this.pregunta = pregunta;
	}
	
	// Métodos
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public PreguntaEncuesta getPregunta() {
		return pregunta;
	}

	public void setPregunta(PreguntaEncuesta pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pregunta, respuesta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatoEncuesta other = (DatoEncuesta) obj;
		return Objects.equals(pregunta, other.pregunta) && Objects.equals(respuesta, other.respuesta);
	}
}
