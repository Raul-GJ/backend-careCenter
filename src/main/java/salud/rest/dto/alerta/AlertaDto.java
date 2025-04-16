package salud.rest.dto.alerta;

import javax.validation.constraints.NotEmpty;

import salud.modelo.Alerta;

public class AlertaDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotEmpty(message = "El campo 'mensaje' no debe estar vacío")
	private String mensaje;
	
	@NotEmpty(message = "El campo 'fecha' no debe estar vacío")
	private String fecha;
	
	// Constructores
	
	public AlertaDto() {
		
	}
	
	// Métodos

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
	
	public static AlertaDto from(Alerta alerta) {
		AlertaDto alertaDto = new AlertaDto();
		alertaDto.setAsunto(alerta.getAsunto());
		alertaDto.setFecha(alerta.getFecha().toString());
		alertaDto.setMensaje(alerta.getMensaje());
		return alertaDto;
	}
}
