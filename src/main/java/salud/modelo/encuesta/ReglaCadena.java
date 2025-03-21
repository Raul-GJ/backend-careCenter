package salud.modelo.encuesta;

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
}
