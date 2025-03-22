package salud.modelo;

public abstract class Sanitario extends Usuario {
	
	// Atributos
	
	private String nCol;
	
	// Constructores
	
	public Sanitario(String nombre, String apellido1, String apellido2, String email, String telefono,
			String nCol) {
		super(nombre, apellido1, apellido2, email, telefono);
		this.nCol = nCol;
	}
	
	// Métodos
	
	public String getNCol() {
		return nCol;
	}

	public void setNCol(String nCol) {
		this.nCol = nCol;
	}
}
