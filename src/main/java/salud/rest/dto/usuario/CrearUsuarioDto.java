package salud.rest.dto.usuario;

import javax.validation.constraints.NotEmpty;

public class CrearUsuarioDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'nombre' no debe estar vacío")
	private String nombre;
	
	@NotEmpty(message = "El campo 'apellidos' no debe estar vacío")
	private String apellidos;
	
	@NotEmpty(message = "El campo 'email' no debe estar vacío")
	private String email;
	
	private String telefono;
	
	@NotEmpty(message = "El campo 'contrasenya' no debe estar vacío")
	private String contrasenya;
	
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
}
