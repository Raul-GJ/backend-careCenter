package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearMedicoDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'atributoTemporal' no debe estar vacío")
	private String atributoTemporal;
	
	// Constructores

	public CrearMedicoDto() {
		super();
	}
	
	// Métodos
	
	public String getAtributoTemporal() {
		return atributoTemporal;
	}

	public void setAtributoTemporal(String atributoTemporal) {
		this.atributoTemporal = atributoTemporal;
	}
	
	
}
