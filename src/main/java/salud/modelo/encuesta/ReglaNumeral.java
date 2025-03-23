package salud.modelo.encuesta;

import java.util.Objects;

public class ReglaNumeral implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	
	// Constructores
	
	public ReglaNumeral() {
		this.tipoDato = TipoDatoEncuesta.NUMERAL;
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
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
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
		ReglaNumeral other = (ReglaNumeral) obj;
		return tipoDato == other.tipoDato;
	}
}
