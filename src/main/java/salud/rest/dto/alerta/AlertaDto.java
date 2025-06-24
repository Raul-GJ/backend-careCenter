package salud.rest.dto.alerta;

import salud.modelo.Alerta;

public class AlertaDto {
	
	// Atributos
	
	private String id;
	private String emisor;
	private String receptor;
	private boolean generadaAutomaticamente;
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

	public boolean isGeneradaAutomaticamente() {
		return generadaAutomaticamente;
	}

	public void setGeneradaAutomaticamente(boolean generadaAutomaticamente) {
		this.generadaAutomaticamente = generadaAutomaticamente;
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
		alertaDto.setEmisor(alerta.getEmisor().getId());
		alertaDto.setReceptor(alerta.getReceptor().getId());
		alertaDto.setGeneradaAutomaticamente(alerta.isGeneradaAutomaticamente());
		alertaDto.setAsunto(alerta.getAsunto());
		alertaDto.setFecha(alerta.getFecha().toString());
		alertaDto.setMensaje(alerta.getMensaje());
		alertaDto.setLeida(alerta.isLeida());
		return alertaDto;
	}	
}
