package salud.modelo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "especialistas")
public class Especialista extends Sanitario {
	
	// Atributos

	private String especialidad;
	
	// Constructores
	
	public Especialista(String nombre, String apellido1, String apellido2, String email, String telefono, String nCol, String especialidad) {
		super(nombre, apellido1, apellido2, email, telefono, nCol);
		this.especialidad = especialidad;
	}
	
	// Métodos
	
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
