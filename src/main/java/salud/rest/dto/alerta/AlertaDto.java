package salud.rest.dto.alerta;

import salud.modelo.Alerta;

public class AlertaDto {
	
	// Atributos
	
	private String id;
	private String asunto;
	private String mensaje;
	private String fecha;
	private boolean leida;
	
	// Constructores
	
	public AlertaDto() {
		
	}
	
	// Métodos
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}
	
	public static AlertaDto from(Alerta alerta) {
		AlertaDto alertaDto = new AlertaDto();
		alertaDto.setId(alerta.getId());
		alertaDto.setAsunto(alerta.getAsunto());
		alertaDto.setFecha(alerta.getFecha().toString());
		alertaDto.setMensaje(alerta.getMensaje());
		alertaDto.setLeida(alerta.isLeida());
		return alertaDto;
	}	
}
