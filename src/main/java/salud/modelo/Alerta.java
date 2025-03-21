package salud.modelo;

import java.time.LocalDateTime;

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
	
	// Constructores
	
	public Alerta(String asunto, String mensaje, LocalDateTime fecha) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
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
}
