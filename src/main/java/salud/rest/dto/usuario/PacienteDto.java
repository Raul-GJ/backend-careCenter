package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.LinkedList;

import salud.modelo.Paciente;

public class PacienteDto extends UsuarioDto {
	
	// Atributos
	
	private String medicoCabecera;
	private Collection<String> especialistas;
	private Collection<String> seguimientos;
	
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

	public Collection<String> getEspecialistas() {
		return especialistas;
	}


	public void setEspecialistas(Collection<String> especialistas) {
		this.especialistas = especialistas;
	}
	
	public Collection<String> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<String> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public static PacienteDto from(Paciente paciente) {
		PacienteDto dto = new PacienteDto();
		dto.setId(paciente.getId());
		dto.setNombre(paciente.getNombre());
		dto.setApellidos(paciente.getApellidos());
		dto.setEmail(paciente.getEmail());
		dto.setTelefono(paciente.getTelefono());
		if (paciente.getMedicoCabecera() != null)
			dto.setMedicoCabecera(paciente.getMedicoCabecera().getId());
		dto.setTipo(paciente.getTipo().toString());
		
		Collection<String> especialistas = new LinkedList<String>();
		Collection<String> seguimientos = new LinkedList<String>();
		Collection<String> alertas = new LinkedList<String>();
		
		paciente.getAlertas().forEach(a -> alertas.add(a.getId()));
		paciente.getEspecialistas().forEach(e -> especialistas.add(e.getId()));
		paciente.getSeguimientos().forEach(s -> seguimientos.add(s.getId()));
		
		dto.setAlertas(alertas);
		dto.setEspecialistas(especialistas);
		dto.setSeguimientos(seguimientos);
		
		return dto;
	}
}
