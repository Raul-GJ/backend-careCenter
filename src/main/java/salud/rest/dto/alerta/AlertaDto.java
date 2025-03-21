package salud.rest.dto.alerta;

import salud.modelo.Alerta;

public class AlertaDto {
	
	// Atributos
	
	private String asunto;
	private String mensaje;
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
