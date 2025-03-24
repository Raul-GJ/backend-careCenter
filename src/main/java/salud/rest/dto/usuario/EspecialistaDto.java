package salud.rest.dto.usuario;

import java.util.LinkedList;
import java.util.List;

import salud.modelo.Especialista;

public class EspecialistaDto extends SanitarioDto {
	
	// Atributos
	
	private String especialidad;
	
	// Constructores
	
	public EspecialistaDto() {
		super();
	}
	
	// Métodos
	
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public static EspecialistaDto from(Especialista especialista) {
		EspecialistaDto dto = new EspecialistaDto();
		dto.setNombre(especialista.getNombre());
		dto.setApellido1(especialista.getApellido1());
		dto.setApellido2(especialista.getApellido2());
		dto.setEmail(especialista.getEmail());
		dto.setTelefono(especialista.getTelefono());
		dto.setnCol(especialista.getNCol());
		dto.setEspecialidad(especialista.getEspecialidad());
		
		List<String> pacientes = new LinkedList<String>();
		especialista.getPacientes().forEach(p -> pacientes.add(p.getId()));
		dto.setPacientes(pacientes);
		
		return dto;
	}
}
