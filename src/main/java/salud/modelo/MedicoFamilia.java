package salud.modelo;

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
}
