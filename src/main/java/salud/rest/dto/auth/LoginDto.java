package salud.rest.dto.auth;

import javax.validation.constraints.NotEmpty;

public class LoginDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'correo' no puede estar vacío")
	private String correo;
	
	@NotEmpty(message = "El campo 'contraseña' no puede estar vacío")
	private String contrasenya;
	
	// Constructores
	
	public LoginDto() {
		super();
	}
	
	// Métodos
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
}
