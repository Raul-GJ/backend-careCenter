package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearNotaPacienteDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'sanitario' no debe estar vacío")
	private String sanitario;
	
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotEmpty(message = "El campo 'contenido' no debe estar vacío")
	private String contenido;
	
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}
}
