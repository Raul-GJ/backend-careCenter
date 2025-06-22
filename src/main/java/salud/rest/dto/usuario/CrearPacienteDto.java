package salud.rest.dto.usuario;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;

public class CrearPacienteDto extends CrearUsuarioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'nss' no debe estar vacío")
	private String nss; // Número de seguridad social
	private Collection<String> alergias;
	private Collection<String> tratamientos;
	private Collection<NotaPacienteDto> notas;
	
	// Constructores
	
	public CrearPacienteDto() {
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
}
