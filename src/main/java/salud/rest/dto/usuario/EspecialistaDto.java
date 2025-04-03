package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import salud.modelo.Especialista;

public class EspecialistaDto extends SanitarioDto {
	
	// Atributos
	
	private String especialidad;
	private Map<String, Collection<String>> estudios;
	private Collection<String> plantillas;
	private Collection<String> consultas;
	
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
	
	public Map<String, Collection<String>> getEstudios() {
		return estudios;
	}

	public void setEstudios(Map<String, Collection<String>> estudios) {
		this.estudios = estudios;
	}

	public Collection<String> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(Collection<String> plantillas) {
		this.plantillas = plantillas;
	}

	public Collection<String> getConsultas() {
		return consultas;
	}

	public void setConsultas(Collection<String> consultas) {
		this.consultas = consultas;
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
		
		Map<String, Collection<String>> estudios = new HashMap<String, Collection<String>>();
		especialista.getEstudios().forEach((r, e) -> estudios.put(r.toString(), 
				e.stream().map(es -> es.getId()).toList()));
		dto.setEstudios(estudios);
		
		List<String> plantillas = new LinkedList<String>();
		especialista.getPlantillas().forEach(p -> plantillas.add(p.getId()));
		dto.setPlantillas(plantillas);
		
		List<String> consultas = new LinkedList<String>();
		especialista.getConsultas().forEach(c -> consultas.add(c.getId()));
		dto.setConsultas(consultas);
		
		return dto;
	}
}
