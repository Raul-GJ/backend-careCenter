package salud.rest.dto.asignacion;

import javax.validation.constraints.NotEmpty;

public class CrearAsignacionEstudioDto {
	
	//Atributos
	
	@NotEmpty(message = "El campo 'especialista' no debe estar vacío")
	private String especialista;
	
	@NotEmpty(message = "El campo 'estudio' no debe estar vacío")
	private String estudio;
	
	@NotEmpty(message = "El campo 'rol' no debe estar vacío")
	private String rol;
	
	// Constructores
	
	public CrearAsignacionEstudioDto() {

	}
	
	// Métodos
	
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

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
}
