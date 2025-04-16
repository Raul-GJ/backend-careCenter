package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearEspecialistaDto extends CrearSanitarioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'especialidad' no debe estar vacío")
	private String especialidad;

	// Constructores
	
	public CrearEspecialistaDto() {
		super();
	}
	
	// Métodos
	
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
