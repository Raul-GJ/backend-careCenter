package salud.rest.dto.usuario;

import java.util.Collection;
import java.util.LinkedList;

import salud.modelo.Paciente;

public class PacienteDto extends UsuarioDto {
	
	// Atributos
	
	private String nss; // Número de seguridad social
	private Collection<String> alergias;
	private Collection<String> tratamientos;
	private Collection<NotaPacienteDto> notas;

	private String medicoCabecera;
	private Collection<String> especialistas;
	private Collection<String> seguimientos;
	
	// Constructores

	public PacienteDto() {
		super();
	}

	// Métodos
	
	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Collection<String> getAlergias() {
		return alergias;
	}

	public void setAlergias(Collection<String> alergias) {
		this.alergias = alergias;
	}

	public Collection<String> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Collection<String> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Collection<NotaPacienteDto> getNotas() {
		return notas;
	}

	public void setNotas(Collection<NotaPacienteDto> notas) {
		this.notas = notas;
	}

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
		dto.setDireccion(paciente.getDireccion());
		dto.setDni(paciente.getDni());
		dto.setFechaNacimiento(paciente.getFechaNacimiento().toString());
		dto.setSexo(paciente.getSexo());
		
		dto.setNss(paciente.getNss());
		if (paciente.getMedicoCabecera() != null)
			dto.setMedicoCabecera(paciente.getMedicoCabecera().getId());
		dto.setTipo(paciente.getTipo().toString());
		
		Collection<String> especialistas = new LinkedList<String>();
		Collection<String> seguimientos = new LinkedList<String>();
		Collection<String> alergias = new LinkedList<String>();
		Collection<String> tratamientos = new LinkedList<String>();
		Collection<NotaPacienteDto> notas = new LinkedList<NotaPacienteDto>(); 
		
		paciente.getEspecialistas().forEach(e -> especialistas.add(e.getId()));
		paciente.getSeguimientos().forEach(s -> seguimientos.add(s.getId()));
		paciente.getAlergias().forEach(a -> alergias.add(a));
		paciente.getTratamientos().forEach(t -> tratamientos.add(t));
		paciente.getNotas().forEach(n -> notas.add(NotaPacienteDto.from(n)));
		
		dto.setEspecialistas(especialistas);
		dto.setSeguimientos(seguimientos);
		dto.setAlergias(alergias);
		dto.setTratamientos(tratamientos);
		dto.setNotas(notas);
		
		return dto;
	}
	
	public static PacienteDto construirSinNotasPrivadas(Paciente paciente) {
		PacienteDto dto = new PacienteDto();
		dto.setId(paciente.getId());
		dto.setNombre(paciente.getNombre());
		dto.setApellidos(paciente.getApellidos());
		dto.setEmail(paciente.getEmail());
		dto.setTelefono(paciente.getTelefono());
		dto.setDireccion(paciente.getDireccion());
		dto.setDni(paciente.getDni());
		dto.setFechaNacimiento(paciente.getFechaNacimiento().toString());
		dto.setSexo(paciente.getSexo());
		
		dto.setNss(paciente.getNss());
		if (paciente.getMedicoCabecera() != null)
			dto.setMedicoCabecera(paciente.getMedicoCabecera().getId());
		dto.setTipo(paciente.getTipo().toString());
		
		Collection<String> especialistas = new LinkedList<String>();
		Collection<String> seguimientos = new LinkedList<String>();
		Collection<String> alergias = new LinkedList<String>();
		Collection<String> tratamientos = new LinkedList<String>();
		Collection<NotaPacienteDto> notas = new LinkedList<NotaPacienteDto>(); 
		
		paciente.getEspecialistas().forEach(e -> especialistas.add(e.getId()));
		paciente.getSeguimientos().forEach(s -> seguimientos.add(s.getId()));
		paciente.getAlergias().forEach(a -> alergias.add(a));
		paciente.getTratamientos().forEach(t -> tratamientos.add(t));
		paciente.getNotas().stream().filter(n -> !n.isPrivado()).forEach(
				n -> notas.add(NotaPacienteDto.from(n)));
		
		dto.setEspecialistas(especialistas);
		dto.setSeguimientos(seguimientos);
		dto.setAlergias(alergias);
		dto.setTratamientos(tratamientos);
		dto.setNotas(notas);
		
		return dto;
	}
}
