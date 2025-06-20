package salud.modelo;

public class Admin extends Usuario {

	public Admin(String nombre, String apellidos, String email, String telefono, String contrasenya) {
		super(nombre, apellidos, email, telefono, TipoUsuario.ADMIN, contrasenya);
	}

}
