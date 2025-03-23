package salud.modelo.encuesta;

import java.util.Objects;

public class PreguntaEncuesta {

	// Atributos
	
	private String pregunta;
	private ReglaDatoEncuesta regla;
	
	// Constructores
	
	public PreguntaEncuesta(String pregunta, ReglaDatoEncuesta regla) {
		super();
		this.pregunta = pregunta;
		this.regla = regla;
	}
	
	// Métodos
	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public boolean testRespuesta(String respuesta) {
		if (regla == null)
			return true;
		return regla.test(respuesta);
	}

	public ReglaDatoEncuesta getRegla() {
		return regla;
	}

	public void setRegla(ReglaDatoEncuesta regla) {
		this.regla = regla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pregunta, regla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreguntaEncuesta other = (PreguntaEncuesta) obj;
		return Objects.equals(pregunta, other.pregunta) && Objects.equals(regla, other.regla);
	}
}
