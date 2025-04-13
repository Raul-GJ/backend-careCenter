package salud.rest.dto.estudio;

import javax.validation.constraints.NotEmpty;

public class AgregarEspecialistaDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'especialista' no debe estar vacío")
	private String especialista;
	@NotEmpty(message = "El campo 'rol' no debe estar vacío")
	private String rol;
	
	// Constructores
	
	public AgregarEspecialistaDto() {
		
	}

	public String getEspecialista() {
		return especialista;
	}

	public void setEspecialista(String especialista) {
		this.especialista = especialista;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
