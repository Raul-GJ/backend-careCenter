package salud.rest.dto.formulario;

import java.util.List;

public class RellenarFormularioDto {
	
	// Atributos
	
	private List<String> respuestas;
	
	// Constructores
	
	public RellenarFormularioDto() {
		super();
	}
	
	// Métodos
	
	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}
}
