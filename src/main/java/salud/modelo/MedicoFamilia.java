package salud.modelo;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicos")
public class MedicoFamilia extends Sanitario {

	// Atributos
	
	private String atributoTemporal;
	
	// Constructores
	
	public MedicoFamilia(String nombre, String apellido1, String apellido2, String email, 
			String telefono, String nCol, String atributoTemporal) {
		super(nombre, apellido1, apellido2, email, telefono, nCol);
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
		MedicoFamilia other = (MedicoFamilia) obj;
		return Objects.equals(atributoTemporal, other.atributoTemporal);
	}
}
