package salud.rest.dto.alerta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import salud.modelo.Alerta;

public class AlertaDto {
	
	// Atributos
	
	@NotNull(message = "El campo 'asunto' es obligatorio")
	@NotEmpty(message = "El campo 'asunto' no debe estar vacío")
	private String asunto;
	
	@NotNull(message = "El campo 'mensaje' es obligatorio")
	@NotEmpty(message = "El campo 'mensaje' no debe estar vacío")
	private String mensaje;
	
	@NotNull(message = "El campo 'fecha' es obligatorio")
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
