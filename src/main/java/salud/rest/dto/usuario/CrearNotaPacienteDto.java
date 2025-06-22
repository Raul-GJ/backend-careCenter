package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CrearNotaPacienteDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'sanitario' no debe estar vacío")
	private String sanitario;
	
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotEmpty(message = "El campo 'nota' no debe estar vacío")
	private String nota;
	
	@NotNull(message = "El campo 'privado' no debe ser nulo")
	private Boolean privado;
	
	// Constructores
	
	public CrearNotaPacienteDto() {
		
	}
	
	// Métodos

	public String getSanitario() {
		return sanitario;
	}

	public void setSanitario(String sanitario) {
		this.sanitario = sanitario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}
}
