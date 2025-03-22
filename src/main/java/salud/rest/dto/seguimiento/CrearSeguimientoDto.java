package salud.rest.dto.seguimiento;

public class CrearSeguimientoDto {

	// Atributos

	private String fecha;
	private String plazo;
	private String plantilla;
	
	// Constructores
	
	public CrearSeguimientoDto() {

	}
	
	// Métodos

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	
	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
}
