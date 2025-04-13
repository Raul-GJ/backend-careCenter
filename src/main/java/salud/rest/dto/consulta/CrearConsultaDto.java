package salud.rest.dto.consulta;

import javax.validation.constraints.NotEmpty;

public class CrearConsultaDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'emisor' no debe estar vacío")
	private String emisor;
	
	@NotEmpty(message = "El campo 'receptor' no debe estar vacío")
	private String receptor;
	
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotEmpty(message = "El campo 'mensaje' no debe estar vacío")
	private String mensaje;
	
	// Constructores
	
	public CrearConsultaDto() {
		
	}
	
	// Métodos
	
	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
