package salud.rest.dto.usuario;

import salud.modelo.Usuario;

public class UsuarioDto {

	// Atributos
	
	private String id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private String fechaNacimiento;
	private String sexo;
	private String direccion;
	private String dni;
	private String tipo;
	
	// Constructores

	public UsuarioDto() {

	}
	
	// Métodos
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public static UsuarioDto from(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNombre(usuario.getNombre());
		usuarioDto.setApellidos(usuario.getApellidos());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setTelefono(usuario.getTelefono());
		usuarioDto.setDireccion(usuario.getDireccion());
		usuarioDto.setDni(usuario.getDni());
		usuarioDto.setSexo(usuario.getSexo());
		usuarioDto.setFechaNacimiento(usuario.getFechaNacimiento().toString());
		usuarioDto.setTipo(usuario.getTipo().toString());
		
		return usuarioDto;
	}

	
}
