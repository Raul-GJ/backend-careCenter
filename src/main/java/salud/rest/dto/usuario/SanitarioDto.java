package salud.rest.dto.usuario;

public class SanitarioDto extends UsuarioDto {
	
	// Atributos
	
	private String nCol;
	
	// Constructores
	
	public SanitarioDto() {
		super();
	}
	
	// Métodos

	public String getnCol() {
		return nCol;
	}

	public void setnCol(String nCol) {
		this.nCol = nCol;
	}
	
}
