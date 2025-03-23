package salud.modelo.encuesta;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(nombre, tipoDato);
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
		return Objects.equals(nombre, other.nombre) && tipoDato == other.tipoDato;
	}
}
