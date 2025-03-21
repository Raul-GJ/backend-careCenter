package salud.modelo;

import java.time.DateTimeException;

public class Tiempo {
	
	// Atributos
	
	private int dias;
	private int horas;
	private int minutos;
	private int segundos;
	
	// Constructores
	
	public Tiempo(int dias, int horas, int minutos, int segundos) {
		super();
		this.dias = dias;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	// Métodos
	
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	public static Tiempo parse(String str) throws DateTimeException {
		String[] valores = str.split(":");
		if (valores.length != 4)
			throw new DateTimeException("Cadena inválida");
		
		int dias = Integer.parseInt(valores[0]);
		int horas = Integer.parseInt(valores[1]);
		int minutos = Integer.parseInt(valores[2]);
		int segundos = Integer.parseInt(valores[3]);
		
		if (dias < 0)
			throw new DateTimeException("Dias inválidos");
		if (horas > 23 || horas < 0)
			throw new DateTimeException("Horas inválidas");
		if (minutos > 59 || minutos < 0)
			throw new DateTimeException("Minutos inválidos");
		if (horas > 59 || horas < 0)
			throw new DateTimeException("Segundos inválidos");
		
		return new Tiempo(dias, horas, minutos, segundos);
	}
}
