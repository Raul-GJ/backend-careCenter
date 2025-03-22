package salud.rest.dto.usuario;

public class CrearPacienteDto extends UsuarioDto {
	
	// Atributos
	
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
