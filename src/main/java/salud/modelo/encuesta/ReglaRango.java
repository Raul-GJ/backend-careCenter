package salud.modelo.encuesta;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(maxValue, minValue, tipoDato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReglaRango other = (ReglaRango) obj;
		return Double.doubleToLongBits(maxValue) == Double.doubleToLongBits(other.maxValue)
				&& Double.doubleToLongBits(minValue) == Double.doubleToLongBits(other.minValue)
				&& tipoDato == other.tipoDato;
	}
}
