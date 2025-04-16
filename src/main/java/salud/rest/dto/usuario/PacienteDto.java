package salud.rest.dto.usuario;

import java.util.LinkedList;
import java.util.List;

import salud.modelo.Paciente;

public class PacienteDto extends UsuarioDto {
	
	// Atributos
	
	private String medicoCabecera;
	private List<String> alertas;
	private List<String> especialistas;
	private List<String> seguimientos;
	
	// Constructores

	public PacienteDto() {
		super();
	}

	// Métodos

	public String getMedicoCabecera() {
		return medicoCabecera;
	}


	public void setMedicoCabecera(String medicoCabecera) {
		this.medicoCabecera = medicoCabecera;
	}


	public List<String> getAlertas() {
		return alertas;
	}


	public void setAlertas(List<String> alertas) {
		this.alertas = alertas;
	}

	public List<String> getEspecialistas() {
		return especialistas;
	}


	public void setEspecialistas(List<String> especialistas) {
		this.especialistas = especialistas;
	}
	
	public List<String> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(List<String> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public static PacienteDto from(Paciente paciente) {
		PacienteDto dto = new PacienteDto();
		dto.setNombre(paciente.getNombre());
		dto.setApellido1(paciente.getApellido1());
		dto.setApellido2(paciente.getApellido2());
		dto.setEmail(paciente.getEmail());
		dto.setTelefono(paciente.getTelefono());
		dto.setMedicoCabecera(paciente.getMedicoCabecera().getId());
		
		List<String> alertas = new LinkedList<String>();
		List<String> especialistas = new LinkedList<String>();
		List<String> seguimientos = new LinkedList<String>();
		
		paciente.getAlertas().forEach(a -> alertas.add(a.getId()));
		paciente.getEspecialistas().forEach(e -> especialistas.add(e.getId()));
		paciente.getSeguimientos().forEach(s -> seguimientos.add(s.getId()));
		
		dto.setAlertas(alertas);
		dto.setEspecialistas(especialistas);
		dto.setSeguimientos(seguimientos);
		
		return dto;
	}
}
