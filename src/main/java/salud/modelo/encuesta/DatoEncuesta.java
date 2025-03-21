package salud.modelo.encuesta;

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
}
