package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class AlertaPeriodica extends Alerta {

	// Atributos
	
	private LocalDateTime fin;
	private Tiempo frecuencia;
	
	// Constructores
	
	public AlertaPeriodica(String asunto, String mensaje, LocalDateTime fecha, LocalDateTime fin,
			Tiempo frecuencia) {
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
	
	public Tiempo getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Tiempo frecuencia) {
		this.frecuencia = frecuencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fin, frecuencia);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertaPeriodica other = (AlertaPeriodica) obj;
		return Objects.equals(fin, other.fin) && Objects.equals(frecuencia, other.frecuencia);
	}
}
