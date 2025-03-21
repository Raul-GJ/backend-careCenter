package salud.modelo.encuesta;

import java.util.Collection;

public class ReglaEnum implements ReglaDatoEncuesta {

	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	private Collection<String> values;
	
	// Constructores
	
	public ReglaEnum(Collection<String> values) {
		super();
		this.tipoDato = TipoDatoEncuesta.ENUMERADO;
		this.values = values;
	}
	
	// Métodos
	
	public TipoDatoEncuesta getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatoEncuesta tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Collection<String> getValues() {
		return values;
	}

	public void setValues(Collection<String> values) {
		this.values = values;
	}
	
	public void addValue(String value) {
		this.values.add(value);
	}
	
	public void removeValue(String value) {
		this.values.remove(value);
	}
	
	@Override
	public boolean test(String value) {
		return values.contains(value);
	}
}
