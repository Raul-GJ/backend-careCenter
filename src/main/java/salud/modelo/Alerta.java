package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alertas")
public class Alerta {
	
	// Atributos
	
	@Id
	private String id;
	private String asunto;
	private String mensaje;
	private LocalDateTime fecha;
	private boolean leida;
	
	// Constructores
	
	public Alerta(String asunto, String mensaje, LocalDateTime fecha) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.leida = false;
	}
	
	// Métodos

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(asunto, fecha, id, mensaje);
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
		return Objects.equals(asunto, other.asunto) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && Objects.equals(mensaje, other.mensaje);
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}
}
