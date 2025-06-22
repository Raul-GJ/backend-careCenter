package salud.modelo;

import java.time.LocalDate;

public class Medico extends Sanitario {

	// Atributos
	
	// Constructores
	
	public Medico(String nombre, String apellidos, String email, String telefono, LocalDate fechaNacimiento,
			String sexo, String direccion, String dni, String contrasenya, String nCol,
			String centroDeSalud) {
		super(nombre, apellidos, email, telefono, fechaNacimiento, sexo, direccion, dni,
				TipoUsuario.MEDICO, contrasenya, nCol, centroDeSalud);
	}

	// Métodos
	
}
