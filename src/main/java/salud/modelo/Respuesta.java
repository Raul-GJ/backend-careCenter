package salud.modelo;

import java.time.LocalDateTime;

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
	
}
