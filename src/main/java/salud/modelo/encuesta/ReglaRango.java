package salud.modelo.encuesta;

public class ReglaRango implements ReglaDatoEncuesta {
	
	// Atributos
	
	private TipoDatoEncuesta tipoDato;
	private double minValue;
	private double maxValue;
	
	// Constructores
	
	public ReglaRango(double minValue, double maxValue) {
		super();
		this.tipoDato = TipoDatoEncuesta.RANGO;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	// Métodos
	
	public TipoDatoEncuesta getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatoEncuesta tipoDato) {
		this.tipoDato = tipoDato;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public boolean test(String value) {
		double val;
		try {
			val = Double.parseDouble(value);
			if (val < maxValue && val > minValue) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
