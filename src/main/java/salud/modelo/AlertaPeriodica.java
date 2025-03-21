package salud.modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AlertaPeriodica extends Alerta {

	// Atributos
	
	private LocalDateTime fin;
	private LocalTime frecuencia;
	
	// Constructores
	
	public AlertaPeriodica(String asunto, String mensaje, LocalDateTime fecha, LocalDateTime fin,
			LocalTime frecuencia) {
		super(asunto, mensaje, fecha);
		this.fin = fin;
		this.frecuencia = frecuencia;
	}
	
	// Métodos
	
	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}
	
	public LocalTime getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(LocalTime frecuencia) {
		this.frecuencia = frecuencia;
	}
}
