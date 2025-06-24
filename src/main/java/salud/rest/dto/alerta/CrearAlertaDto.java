package salud.rest.dto.alerta;

import javax.validation.constraints.NotEmpty;

public class CrearAlertaDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'emisor' no debe estar vacío")
	private String emisor;
	
	@NotEmpty(message = "El campo 'receptor' no debe estar vacío")
	private String receptor;
	
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotEmpty(message = "El campo 'mensaje' no debe estar vacío")
	private String mensaje;
	
	@NotEmpty(message = "El campo 'fecha' no debe estar vacío")
	private String fecha;
	
	// Constructores
	
	public CrearAlertaDto() {
		
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
