package salud.rest.dto.usuario;

import java.util.List;

public class SanitarioDto extends UsuarioDto {
	
	// Atributos
	
	private String nCol;
	private List<String> pacientes;
	
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
	
	public List<String> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<String> pacientes) {
		this.pacientes = pacientes;
	}
}
