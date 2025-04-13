package salud.rest.dto.estudio;

import salud.modelo.InfoEstudio;

public class InfoEstudioDto {
	
	//Atributos
	
	private String rol;
	private String estudio;
	
	// Constructores
	
	public InfoEstudioDto() {
		
	}
	
	// Métodos
	
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
	
	public static InfoEstudioDto from(InfoEstudio infoEstudio) {
		InfoEstudioDto dto = new InfoEstudioDto();
		dto.setEstudio(infoEstudio.getEstudio().getId());
		dto.setRol(infoEstudio.getRol().toString());
		return dto;
	}
}
