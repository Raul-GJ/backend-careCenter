package salud.rest.dto.usuario;

import java.util.LinkedList;
import java.util.List;

import salud.modelo.Medico;

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
	
	public static MedicoDto from(Medico medico) {
		MedicoDto dto = new MedicoDto();
		dto.setId(medico.getId());
		dto.setNombre(medico.getNombre());
		dto.setApellido1(medico.getApellido1());
		dto.setApellido2(medico.getApellido2());
		dto.setEmail(medico.getEmail());
		dto.setTelefono(medico.getTelefono());
		dto.setnCol(medico.getNCol());
		dto.setAtributoTemporal(medico.getAtributoTemporal());
		
		List<String> pacientes = new LinkedList<String>();
		medico.getPacientes().forEach(p -> pacientes.add(p.getId()));
		dto.setPacientes(pacientes);
		
		return dto;
	}
}
