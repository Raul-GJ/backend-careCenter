package salud.rest.dto.seguimiento;

import salud.modelo.Seguimiento;
import salud.rest.dto.formulario.FormularioDto;

public class SeguimientoDto {

	// Atributos

	private String id;
	private String fecha;
	private String plazo;
	private FormularioDto formulario;
	private String motivo;
	
	// Constructores

	public SeguimientoDto() {

	}
	
	// Métodos
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public static SeguimientoDto from(Seguimiento seguimiento) {
		SeguimientoDto seguimientoDto = new SeguimientoDto();
		seguimientoDto.setId(seguimiento.getId());
		seguimientoDto.setFecha(seguimiento.getFecha().toString());
		seguimientoDto.setPlazo(seguimiento.getPlazo().toString());
		seguimientoDto.setFormulario(FormularioDto.from(seguimiento.getFormulario()));
		seguimientoDto.setMotivo(seguimiento.getMotivo());
		return seguimientoDto;
	}
}
