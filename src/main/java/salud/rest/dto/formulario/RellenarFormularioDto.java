package salud.rest.dto.formulario;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class RellenarFormularioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'respuestas' no debe estar vacío")
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
