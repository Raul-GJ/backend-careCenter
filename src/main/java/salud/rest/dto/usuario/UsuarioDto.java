package salud.rest.dto.usuario;

import salud.modelo.Usuario;

public class UsuarioDto {

	// Atributos
	
	private String id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	
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
	
	public static UsuarioDto from(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNombre(usuario.getNombre());
		usuarioDto.setApellidos(usuario.getApellidos());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setTelefono(usuario.getTelefono());
		return usuarioDto;
	}
}
