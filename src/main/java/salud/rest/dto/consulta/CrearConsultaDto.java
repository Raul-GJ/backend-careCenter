package salud.rest.dto.consulta;

public class CrearConsultaDto {

	// Atributos
	
	private String emisor;
	private String receptor;
	private String asunto;
	private String mensaje;
	
	// Constructores
	
	public CrearConsultaDto() {
		
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
}
