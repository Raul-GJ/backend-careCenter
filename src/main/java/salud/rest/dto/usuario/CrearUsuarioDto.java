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
	
	@NotEmpty(message = "El campo 'fechaNacimiento' no debe estar vacío")
	private String fechaNacimiento;
	
	@NotEmpty(message = "El campo 'sexo' no debe estar vacío")
	private String sexo;
	
	@NotEmpty(message = "El campo 'direccion' no debe estar vacío")
	private String direccion;
	
	@NotEmpty(message = "El campo 'dni' no debe estar vacío")
	private String dni;
	
	@NotEmpty(message = "El campo 'contrasenya' no debe estar vacío")
	private String contrasenya;
	
	// Constructores
	
	public CrearUsuarioDto() {

	}
	
	// Métodos

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

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
