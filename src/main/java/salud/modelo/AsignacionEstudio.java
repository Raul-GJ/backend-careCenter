package salud.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asignacionEstudio")
public class AsignacionEstudio {
	
	// Atributos
	
	@Id
	private String id;
	@DBRef
	private Especialista especialista;
	@DBRef
	private Estudio estudio;
	private RolEstudio rol;
	
	// Constructores
	
	public AsignacionEstudio(Especialista especialista, Estudio estudio, RolEstudio rol) {
		super();
		this.especialista = especialista;
		this.estudio = estudio;
		this.rol = rol;
	}
	
	// Métodos

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Especialista getEspecialista() {
		return especialista;
	}

	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public RolEstudio getRol() {
		return rol;
	}

	public void setRol(RolEstudio rol) {
		this.rol = rol;
	}
}
