package salud.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultas")
public class Consulta {
	
	// Atributos
	
	@Id
	private String id;
	private String asunto;
	private String mensaje;
	private LocalDateTime fecha;
	private Respuesta respuesta;
	
	// Constructores
	
	public Consulta(String asunto, String mensaje) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = LocalDateTime.now();
		this.respuesta = null;
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
	
	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
}
