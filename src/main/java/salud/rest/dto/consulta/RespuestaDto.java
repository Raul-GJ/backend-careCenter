package salud.rest.dto.consulta;

import salud.modelo.Respuesta;

public class RespuestaDto {

	// Atributos
	
	private String mensaje;
	private String fecha;
	
	// Construcctores
	
	public RespuestaDto() {
		
	}
	
	// Métodos

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
	
	public static RespuestaDto from(Respuesta respuesta) {
		RespuestaDto respuestaDto = new RespuestaDto();
		respuestaDto.setFecha(respuesta.getFecha().toString());
		respuestaDto.setMensaje(respuesta.getMensaje());
		return respuestaDto;
	}
}
