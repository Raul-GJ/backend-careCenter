package salud.rest.dto.usuario;

import salud.modelo.NotaPaciente;

public class NotaPacienteDto {
	
	// Atributos
	
	private String sanitario;
	private String asunto;
	private String nota;
	
	// Constructores
	
	public NotaPacienteDto() {
		
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
	
	public static NotaPacienteDto from(NotaPaciente nota) {
		NotaPacienteDto dto = new NotaPacienteDto();
		dto.setAsunto(nota.getAsunto());
		dto.setNota(nota.getNota());
		dto.setSanitario(nota.getSanitario().getId());
		
		return dto;
	}
}
