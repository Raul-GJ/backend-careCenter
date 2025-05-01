package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import salud.modelo.Medico;

public class MedicoDto extends SanitarioDto {
	
	// Atributos
	
	// Constructores
	
	public MedicoDto() {
		super();
	}
	
	// Métodos
	
	public static MedicoDto from(Medico medico) {
		MedicoDto dto = new MedicoDto();
		dto.setId(medico.getId());
		dto.setNombre(medico.getNombre());
		dto.setApellidos(medico.getApellidos());
		dto.setEmail(medico.getEmail());
		dto.setTelefono(medico.getTelefono());
		dto.setnCol(medico.getNCol());
		
		Collection<String> alertas = new LinkedList<String>();
		medico.getAlertas().forEach(a -> alertas.add(a.getId()));
		dto.setAlertas(alertas);
		
		dto.setTipo(medico.getTipo().toString());
		
		List<String> pacientes = new LinkedList<String>();
		medico.getPacientes().forEach(p -> pacientes.add(p.getId()));
		dto.setPacientes(pacientes);
		
		return dto;
	}
}
