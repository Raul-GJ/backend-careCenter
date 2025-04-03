package salud.rest.dto.consulta;

import salud.modelo.Consulta;

public class ConsultaDto {

	// Atributos
	
	private String emisor;
	private String receptor;
	private String asunto;
	private String mensaje;
	private String fecha;
	private RespuestaDto respuesta;
	
	// Constructores

	public ConsultaDto() {
		
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
	
	public RespuestaDto getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaDto respuesta) {
		this.respuesta = respuesta;
	}
	
	public static ConsultaDto from(Consulta consulta) {
		ConsultaDto consultaDto = new ConsultaDto();
		consultaDto.setEmisor(consulta.getEmisor().getId());
		consultaDto.setReceptor(consulta.getReceptor().getId());
		consultaDto.setAsunto(consulta.getAsunto());
		consultaDto.setFecha(consulta.getFecha().toString());
		consultaDto.setMensaje(consulta.getMensaje());
		if (consulta.getRespuesta() != null)
			consultaDto.setRespuesta(RespuestaDto.from(consulta.getRespuesta()));;
		return consultaDto;
	}
}
