package salud.modelo;

import java.util.Objects;

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
	private EstadoInvitacion estado;
	
	// Constructores
	
	public AsignacionEstudio(Especialista especialista, Estudio estudio, RolEstudio rol) {
		super();
		this.especialista = especialista;
		this.estudio = estudio;
		this.rol = rol;
		this.setEstado(EstadoInvitacion.PENDIENTE);
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

	public EstadoInvitacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoInvitacion estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsignacionEstudio other = (AsignacionEstudio) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AsignacionEstudio [id=" + id + ", especialista=" + especialista + ", estudio=" + estudio + ", rol="
				+ rol + ", estado=" + estado + "]";
	}
}
