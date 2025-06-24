package salud.rest.dto.usuario;

import salud.modelo.NotaPaciente;

public class NotaPacienteDto {
	
	// Atributos
	
	private String id;
	private String sanitario;
	private String asunto;
	private String contenido;
	private boolean privado;
	
	// Constructores
	
	public NotaPacienteDto() {
		
	}
	
	// Métodos

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}
	
	public static NotaPacienteDto from(NotaPaciente nota) {
		NotaPacienteDto dto = new NotaPacienteDto();
		dto.setId(nota.getId());
		dto.setAsunto(nota.getAsunto());
		dto.setContenido(nota.getContenido());
		dto.setSanitario(nota.getSanitario().getId());
		dto.setPrivado(nota.isPrivado());
		
		return dto;
	}
}
