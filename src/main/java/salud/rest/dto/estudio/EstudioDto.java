package salud.rest.dto.estudio;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import salud.modelo.Estudio;

public class EstudioDto {

	// Atributos
	
	private String nombre;
	private String descripcion;
	private String fechaAlta;
	private String fechaFin;
	private Collection<String> pacientes;
	private Collection<String> seguimientos;
	private Collection<String> alertas;
	private Map<String, String> especialistas;
	
	// Constructores
	
	public EstudioDto() {

	}
	
	// Métodos

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Collection<String> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<String> pacientes) {
		this.pacientes = pacientes;
	}
	
	public Collection<String> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<String> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Collection<String> getAlertas() {
		return alertas;
	}

	public void setAlertas(Collection<String> alertas) {
		this.alertas = alertas;
	}
	
	public Map<String, String> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Map<String, String> especialistas) {
		this.especialistas = especialistas;
	}
	
	public static EstudioDto from(Estudio estudio) {
		EstudioDto estudioDto = new EstudioDto();
		
		Collection<String> pacientes = new LinkedList<String>();
		Collection<String> alertas = new LinkedList<String>();
		Collection<String> seguimientos = new LinkedList<String>();
		Map<String, String> especialistas = new HashMap<String, String>();
		
		estudio.getPacientes().forEach(p -> pacientes.add(p.getId()));
		estudio.getAlertas().forEach(a -> alertas.add(a.getId()));
		estudio.getSeguimientos().forEach(s -> seguimientos.add(s.getId()));
		estudio.getEspecialistas().forEach((e, r) -> especialistas.put(e.getId(), r.toString()));
		
		estudioDto.setNombre(estudio.getNombre());
		estudioDto.setDescripcion(estudio.getDescripcion());
		estudioDto.setFechaAlta(estudio.getFechaAlta().toString());
		estudioDto.setFechaFin(estudio.getFechaFin().toString());
		
		estudioDto.setPacientes(pacientes);
		estudioDto.setAlertas(alertas);
		estudioDto.setSeguimientos(seguimientos);
		estudioDto.setEspecialistas(especialistas);
		
		return estudioDto;
	}
}
