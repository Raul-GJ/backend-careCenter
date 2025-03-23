package salud.rest.dto.consulta;

public class CrearConsultaDto {

	// Atributos
	
	private String asunto;
	private String mensaje;
	
	// Constructores
	
	public CrearConsultaDto() {
		
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
}
