package salud.rest.dto.asignacion;

import javax.validation.constraints.NotEmpty;

public class ModificarAsignacionDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'rol' no debe estar vacío")
	private String rol;
	
	// Constructores
	
	public ModificarAsignacionDto() {
		
	}
	
	// Métodos

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
