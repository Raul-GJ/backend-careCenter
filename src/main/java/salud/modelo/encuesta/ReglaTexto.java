package salud.modelo.encuesta;

public class ReglaTexto implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	
	// Constructores
	
	public ReglaTexto() {
		this.tipoDato = TipoDatoEncuesta.TEXTO;
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
}
