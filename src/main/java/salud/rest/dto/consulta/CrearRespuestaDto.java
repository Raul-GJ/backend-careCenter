package salud.rest.dto.consulta;

import javax.validation.constraints.NotEmpty;

public class CrearRespuestaDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'mensaje' no debe estar vacío")
	private String mensaje;
	
	// Construcctores
	
	public CrearRespuestaDto() {
		
	}
	
	// Métodos

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
