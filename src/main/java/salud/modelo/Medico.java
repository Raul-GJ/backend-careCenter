package salud.modelo;

public class Medico extends Sanitario {

	// Atributos
	
	// Constructores
	
	public Medico(String nombre, String apellidos, String email, 
			String telefono, String contrasenya, String nCol) {
		super(nombre, apellidos, email, telefono, TipoUsuario.MEDICO, contrasenya, nCol);
	}

	// Métodos
	
}
