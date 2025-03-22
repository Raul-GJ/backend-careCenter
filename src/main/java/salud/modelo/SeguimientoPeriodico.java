package salud.modelo;

import java.time.LocalDateTime;

public class SeguimientoPeriodico extends Seguimiento {

	// Atributos
	
	private LocalDateTime fin;
	private Tiempo frecuencia;
	
	// Constructores
	
	public SeguimientoPeriodico(LocalDateTime fecha, LocalDateTime plazo, Formulario formulario,
			LocalDateTime fin, Tiempo frecuencia) {
		super(fecha, plazo, formulario);
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

}
