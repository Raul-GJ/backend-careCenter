package salud.modelo.encuesta;

import java.util.Objects;

public class ReglaBooleano implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	
	// Constructores
	
	public ReglaBooleano() {
		this.tipoDato = TipoDatoEncuesta.BOOLEANO;
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
		return Boolean.parseBoolean(value);
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
		ReglaBooleano other = (ReglaBooleano) obj;
		return tipoDato == other.tipoDato;
	}
}
