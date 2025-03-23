package salud.modelo;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nCol);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sanitario other = (Sanitario) obj;
		return Objects.equals(nCol, other.nCol);
	}
}
