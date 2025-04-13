package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearPacienteDto extends UsuarioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'medicoCabecera' no debe estar vacío")
	private String medicoCabecera;
	
	// Constructores
	
	public CrearPacienteDto() {
		
	}
	
	// Métodos
	
	public String getMedicoCabecera() {
		return medicoCabecera;
	}

	public void setMedicoCabecera(String medicoCabecera) {
		this.medicoCabecera = medicoCabecera;
	}
	
}
