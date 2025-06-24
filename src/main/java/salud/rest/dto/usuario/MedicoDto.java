package salud.rest.dto.usuario;

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
		dto.setDni(medico.getDni());
		dto.setDireccion(medico.getDireccion());
		dto.setSexo(medico.getSexo());
		dto.setFechaNacimiento(medico.getFechaNacimiento().toString());
		
		dto.setnCol(medico.getNCol());
		dto.setCentroDeSalud(medico.getCentroDeSalud());
		
		dto.setTipo(medico.getTipo().toString());
		
		List<String> pacientes = new LinkedList<String>();
		medico.getPacientes().forEach(p -> pacientes.add(p.getId()));
		dto.setPacientes(pacientes);
		
		return dto;
	}
	
	public static MedicoDto construirMedicoPublico(Medico medico) {
		MedicoDto dto = new MedicoDto();
		dto.setId(medico.getId());
		dto.setNombre(medico.getNombre());
		dto.setApellidos(medico.getApellidos());
		dto.setSexo(medico.getSexo());
		dto.setFechaNacimiento(medico.getFechaNacimiento().toString());
		dto.setCentroDeSalud(medico.getCentroDeSalud());
		
		return dto;
	}
}
