package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearSanitarioDto extends CrearUsuarioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'nCol' no debe estar vacío")
	private String nCol;
	
	@NotEmpty(message = "El campo 'centroDeSalud' no debe estar vacío")
	private String centroDeSalud;
	
	// Constructores

	public CrearSanitarioDto() {
		super();
	}
	
	// Métodos

	public String getnCol() {
		return nCol;
	}

	public void setnCol(String nCol) {
		this.nCol = nCol;
	}

	public String getCentroDeSalud() {
		return centroDeSalud;
	}

	public void setCentroDeSalud(String centroDeSalud) {
		this.centroDeSalud = centroDeSalud;
	}
}
