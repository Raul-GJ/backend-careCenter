package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultas")
public class Consulta {
	
	// Atributos

	@Id
	private String id;
	@DBRef
	private Paciente emisor;
	@DBRef
	private Sanitario receptor;
	private String asunto;
	private String mensaje;
	private LocalDateTime fecha;
	private Respuesta respuesta;
	
	// Constructores
	
	public Paciente getEmisor() {
		return emisor;
	}

	public void setEmisor(Paciente emisor) {
		this.emisor = emisor;
	}

	public Sanitario getReceptor() {
		return receptor;
	}

	public void setReceptor(Sanitario receptor) {
		this.receptor = receptor;
	}

	public Consulta(String asunto, String mensaje, Paciente emisor, Sanitario receptor) {
		super();
		this.emisor = emisor;
		this.receptor = receptor;
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
		if (this.respuesta == null)
			this.respuesta = respuesta;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(asunto, fecha, id, mensaje, respuesta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(asunto, other.asunto) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && Objects.equals(mensaje, other.mensaje)
				&& Objects.equals(respuesta, other.respuesta);
	}
}
