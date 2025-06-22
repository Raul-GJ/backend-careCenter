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
		dto.setId(especialista.getId());
		dto.setNombre(especialista.getNombre());
		dto.setApellidos(especialista.getApellidos());
		dto.setEmail(especialista.getEmail());
		dto.setTelefono(especialista.getTelefono());
		dto.setDireccion(especialista.getDireccion());
		dto.setDni(especialista.getDni());
		dto.setSexo(especialista.getSexo());
		dto.setFechaNacimiento(especialista.getFechaNacimiento().toString());
		
		dto.setnCol(especialista.getNCol());
		dto.setCentroDeSalud(especialista.getCentroDeSalud());
		dto.setEspecialidad(especialista.getEspecialidad());
		
		Collection<String> alertas = new LinkedList<String>();
		especialista.getAlertas().forEach(a -> alertas.add(a.getId()));
		dto.setAlertas(alertas);
		
		dto.setTipo(especialista.getTipo().toString());
		
		List<String> pacientes = new LinkedList<String>();
		especialista.getPacientes().forEach(p -> pacientes.add(p.getId()));
		dto.setPacientes(pacientes);
		
		List<String> plantillas = new LinkedList<String>();
		especialista.getPlantillas().forEach(p -> plantillas.add(p.getId()));
		dto.setPlantillas(plantillas);
		
		return dto;
	}
}
