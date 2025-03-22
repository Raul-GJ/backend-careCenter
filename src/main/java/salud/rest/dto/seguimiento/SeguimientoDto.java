package salud.rest.dto.seguimiento;

import salud.modelo.Seguimiento;
import salud.rest.dto.formulario.FormularioDto;

public class SeguimientoDto {

	// Atributos

	private String fecha;
	private String plazo;
	private FormularioDto formulario;
	
	// Constructores
	
	public SeguimientoDto() {

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
	
	public FormularioDto getFormulario() {
		return formulario;
	}

	public void setFormulario(FormularioDto formulario) {
		this.formulario = formulario;
	}
	
	public static SeguimientoDto from(Seguimiento seguimiento) {
		SeguimientoDto seguimientoDto = new SeguimientoDto();
		seguimientoDto.setFecha(seguimiento.getFecha().toString());
		seguimientoDto.setPlazo(seguimiento.getPlazo().toString());
		seguimientoDto.setFormulario(FormularioDto.from(seguimiento.getFormulario()));
		return seguimientoDto;
	}
}
