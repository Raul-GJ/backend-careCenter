package salud.modelo.encuesta;

public class ReglaBooleano implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	private String nombre;
	
	// Constructores
	
	public ReglaBooleano() {
		this.tipoDato = TipoDatoEncuesta.BOOLEANO;
		this.nombre = "reglaBooleano";
	}
	
	// Métodos
	
	public TipoDatoEncuesta getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatoEncuesta tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean test(String value) {
		return Boolean.parseBoolean(value);
	}
}
