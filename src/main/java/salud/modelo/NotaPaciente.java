package salud.modelo;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notas")
public class NotaPaciente {
	
	// Atributos
	
	@Id
	private String id;
	@DBRef
	private Sanitario sanitario;
	private String asunto;
	private String contenido;
	private boolean privado; // False = El paciente la puede ver, True = El paciente no la puede ver (otros sanitarios si)
	
	// Constructores
	
	public NotaPaciente(Sanitario sanitario, String asunto, String contenido, boolean privado) {
		super();
		this.sanitario = sanitario;
		this.asunto = asunto;
		this.contenido = contenido;
		this.privado = privado;
	}
	
	// Métodos
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Sanitario getSanitario() {
		return sanitario;
	}
	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
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
		NotaPaciente other = (NotaPaciente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "NotaPaciente [id=" + id + ", sanitario=" + sanitario + ", asunto=" + asunto + ", contenido=" + contenido
				+ ", privado=" + privado + "]";
	}
	
	
}
