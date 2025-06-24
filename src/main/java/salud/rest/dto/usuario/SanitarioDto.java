package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.LinkedList;

import salud.modelo.Sanitario;

public class SanitarioDto extends UsuarioDto {
	
	// Atributos
	
	private String nCol;
	private String centroDeSalud;
	private Collection<String> pacientes;
	
	// Constructores

	public SanitarioDto() {
		super();
	}
	
	// Métodos

	public String getnCol() {
		return nCol;
	}

	public void setnCol(String nCol) {
		this.nCol = nCol;
	}
	
	public Collection<String> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<String> pacientes) {
		this.pacientes = pacientes;
	}
	
	public static SanitarioDto from (Sanitario sanitario) {
		SanitarioDto sanitarioDto = new SanitarioDto();
		sanitarioDto.setId(sanitario.getId());
		sanitarioDto.setNombre(sanitario.getNombre());
		sanitarioDto.setApellidos(sanitario.getApellidos());
		sanitarioDto.setEmail(sanitario.getEmail());
		sanitarioDto.setTelefono(sanitario.getTelefono());
		sanitarioDto.setDni(sanitario.getDni());
		sanitarioDto.setDireccion(sanitario.getDireccion());
		sanitarioDto.setSexo(sanitario.getSexo());
		sanitarioDto.setFechaNacimiento(sanitario.getFechaNacimiento().toString());
		
		Collection<String> pacientes = new LinkedList<String>();
		sanitario.getPacientes().forEach(p -> pacientes.add(p.getId()));
		sanitarioDto.setPacientes(pacientes);
		
		sanitarioDto.setTipo(sanitario.getTipo().toString());
		sanitarioDto.setnCol(sanitario.getNCol());
		sanitarioDto.setCentroDeSalud(sanitario.getCentroDeSalud());
		
		return sanitarioDto;
	}

	public String getCentroDeSalud() {
		return centroDeSalud;
	}

	public void setCentroDeSalud(String centroDeSalud) {
		this.centroDeSalud = centroDeSalud;
	}
}
