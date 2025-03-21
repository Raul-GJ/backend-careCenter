package salud.modelo.encuesta;

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
}
