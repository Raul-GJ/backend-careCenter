package salud.rest.dto.consulta;

import salud.modelo.Consulta;

public class ConsultaDto {

	// Atributos
	
	private String asunto;
	private String mensaje;
	private String fecha;
	
	// Constructores
	
	public ConsultaDto() {
		
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
	
	public static ConsultaDto from(Consulta consulta) {
		ConsultaDto consultaDto = new ConsultaDto();
		consultaDto.setAsunto(consulta.getAsunto());
		consultaDto.setFecha(consulta.getFecha().toString());
		consultaDto.setMensaje(consulta.getMensaje());
		return consultaDto;
	}
}
