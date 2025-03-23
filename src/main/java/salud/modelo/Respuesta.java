package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Respuesta {
	
	// Atributos

	private String mensaje;
	private LocalDateTime fecha;
	
	// Construcctores
	
	public Respuesta(String mensaje, LocalDateTime fecha) {
		super();
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	
	// Métodos

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
		return Objects.hash(fecha, mensaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(mensaje, other.mensaje);
	}
	
}
