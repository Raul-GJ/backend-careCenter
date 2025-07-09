package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alertas")
public class Alerta {
	
	// Atributos
	
	@Id
	private String id;
	@DBRef
	private Usuario emisor;
	@DBRef
	private Usuario receptor;
	private boolean generadaAutomaticamente;
	private String asunto;
	private String mensaje;
	private LocalDateTime fecha;
	private boolean leida;
	private String idGrupo; // Solo para alertas de estudios
	
	// Constructores
	
	public Alerta(Usuario emisor, Usuario receptor, boolean generadaAutomaticamente, String asunto, 
			String mensaje, LocalDateTime fecha, String idGrupo) {
		super();
		this.emisor = emisor;
		this.receptor = receptor;
		this.generadaAutomaticamente = generadaAutomaticamente;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.leida = false;
		this.idGrupo = idGrupo;
	}
	
	// Métodos

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public boolean isGeneradaAutomaticamente() {
		return generadaAutomaticamente;
	}

	public void setGeneradaAutomaticamente(boolean generadaAutomaticamente) {
		this.generadaAutomaticamente = generadaAutomaticamente;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
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
		Alerta other = (Alerta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Alerta [id=" + id + ", emisor=" + emisor + ", receptor=" + receptor + ", generadaAutomaticamente="
				+ generadaAutomaticamente + ", asunto=" + asunto + ", mensaje=" + mensaje + ", fecha=" + fecha
				+ ", leida=" + leida + ", idGrupo=" + idGrupo + "]";
	}
}
