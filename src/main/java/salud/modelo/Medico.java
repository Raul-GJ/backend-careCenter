package salud.modelo;

import java.util.Objects;

public class Medico extends Sanitario {

	// Atributos
	
	private String atributoTemporal;
	
	// Constructores
	
	public Medico(String nombre, String apellidos, String email, 
			String telefono, String contrasenya, String nCol, String atributoTemporal) {
		super(nombre, apellidos, email, telefono, TipoUsuario.MEDICO, contrasenya, nCol);
		this.atributoTemporal = atributoTemporal;
	}

	// Métodos
	
	public String getAtributoTemporal() {
		return atributoTemporal;
	}

	public void setAtributoTemporal(String atributoTemporal) {
		this.atributoTemporal = atributoTemporal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atributoTemporal);
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
		Medico other = (Medico) obj;
		return Objects.equals(atributoTemporal, other.atributoTemporal);
	}
}
