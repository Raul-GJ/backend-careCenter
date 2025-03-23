package salud.modelo.encuesta;

import java.util.Objects;

public class ReglaCadena implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	
	// Constructores
	
	public ReglaCadena() {
		this.tipoDato = TipoDatoEncuesta.CADENA;
	}
	
	// Métodos
	
	public TipoDatoEncuesta getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatoEncuesta tipoDato) {
		this.tipoDato = tipoDato;
	}

	@Override
	public boolean test(String value) {
		return value != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipoDato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReglaCadena other = (ReglaCadena) obj;
		return tipoDato == other.tipoDato;
	}
}
