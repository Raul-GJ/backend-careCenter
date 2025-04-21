package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearUsuarioDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'nombre' no debe estar vacío")
	private String nombre;
	
	@NotEmpty(message = "El campo 'apellido1' no debe estar vacío")
	private String apellido1;
	
	private String apellido2;
	
	@NotEmpty(message = "El campo 'email' no debe estar vacío")
	private String email;
	
	private String telefono;
	
	// Constructores
	
	public CrearUsuarioDto() {

	}
	
	// Métodos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
