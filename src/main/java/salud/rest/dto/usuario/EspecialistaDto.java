package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import salud.modelo.Especialista;

public class EspecialistaDto extends SanitarioDto {
	
	// Atributos
	
	private String especialidad;
	private Collection<String> plantillas;
	
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

	public Collection<String> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(Collection<String> plantillas) {
		this.plantillas = plantillas;
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
		
		List<String> plantillas = new LinkedList<String>();
		especialista.getPlantillas().forEach(p -> plantillas.add(p.getId()));
		dto.setPlantillas(plantillas);
		
		return dto;
	}
}
