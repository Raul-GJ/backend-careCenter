package salud.rest.dto.seguimiento;

import javax.validation.constraints.NotEmpty;

public class CrearSeguimientoDto {

	// Atributos

	@NotEmpty(message = "El campo 'fecha' no debe estar vacío")
	private String fecha;
	
	@NotEmpty(message = "El campo 'plazo' no debe estar vacío")
	private String plazo;
	
	@NotEmpty(message = "El campo 'plantilla' no debe estar vacío")
	private String plantilla;
	
	@NotEmpty(message = "El campo 'motivo' no debe estar vacío")
	private String motivo;
	
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
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
