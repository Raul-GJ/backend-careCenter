package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearSanitarioDto extends UsuarioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'nCol' no debe estar vacío")
	private String nCol;
	
	// Constructores

	public CrearSanitarioDto() {

	}
	
	// Métodos

	public String getnCol() {
		return nCol;
	}

	public void setnCol(String nCol) {
		this.nCol = nCol;
	}
}
