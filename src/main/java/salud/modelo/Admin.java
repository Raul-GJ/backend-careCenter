package salud.modelo;

import java.time.LocalDate;

public class Admin extends Usuario {

	public Admin(String nombre, String apellidos, String email, String telefono, LocalDate fechaNacimiento,
			String sexo, String direccion, String dni, String contrasenya) {
		super(nombre, apellidos, email, telefono, fechaNacimiento, sexo, direccion, dni, TipoUsuario.ADMIN, 
				contrasenya);
	}

}
