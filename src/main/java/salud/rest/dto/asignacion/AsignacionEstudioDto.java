package salud.rest.dto.asignacion;

import javax.validation.constraints.NotEmpty;

import salud.modelo.AsignacionEstudio;

public class AsignacionEstudioDto {
	
	//Atributos
	
	@NotEmpty(message = "El campo 'especialista' no debe estar vacío")
	private String especialista;
	
	@NotEmpty(message = "El campo 'estudio' no debe estar vacío")
	private String estudio;
	
	@NotEmpty(message = "El campo 'rol' no debe estar vacío")
	private String rol;
	
	// Constructores
	
	public AsignacionEstudioDto() {

	}
	
	// Métodos
	
	public String getEspecialista() {
		return especialista;
	}

	public void setEspecialista(String especialista) {
		this.especialista = especialista;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
	
	public static AsignacionEstudioDto from(AsignacionEstudio asignacionEstudio) {
		AsignacionEstudioDto dto = new AsignacionEstudioDto();
		dto.setEspecialista(asignacionEstudio.getEspecialista().getId());
		dto.setEstudio(asignacionEstudio.getEstudio().getId());
		dto.setRol(asignacionEstudio.getRol().toString());
		return dto;
	}
}
