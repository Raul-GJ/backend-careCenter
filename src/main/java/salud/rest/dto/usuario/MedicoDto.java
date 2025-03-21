package salud.rest.dto.usuario;

import salud.modelo.MedicoFamilia;

public class MedicoDto extends SanitarioDto {
	
	// Atributos
	
	private String atributoTemporal;
	
	// Constructores
	
	public MedicoDto() {
		super();
	}
	
	// Métodos

	public String getAtributoTemporal() {
		return atributoTemporal;
	}

	public void setAtributoTemporal(String atributoTemporal) {
		this.atributoTemporal = atributoTemporal;
	}
	
	public static MedicoDto from(MedicoFamilia medico) {
		MedicoDto dto = new MedicoDto();
		dto.setNombre(medico.getNombre());
		dto.setApellido1(medico.getApellido1());
		dto.setApellido2(medico.getApellido2());
		dto.setEmail(medico.getEmail());
		dto.setTelefono(medico.getTelefono());
		dto.setnCol(medico.getNCol());
		dto.setAtributoTemporal(medico.getAtributoTemporal());
		
		return dto;
	}
}
