package salud.modelo;

public class InfoEstudio {
	
	// Atributos
	
	private RolEstudio rol;
	private Estudio estudio;
	
	// Constructores
	
	public InfoEstudio(RolEstudio rol, Estudio estudio) {
		super();
		this.rol = rol;
		this.estudio = estudio;
	}
	
	// Métodos

	public RolEstudio getRol() {
		return rol;
	}

	public void setRol(RolEstudio rol) {
		this.rol = rol;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
}
