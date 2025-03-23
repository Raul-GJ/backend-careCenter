package salud.modelo;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(especialidad);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialista other = (Especialista) obj;
		return Objects.equals(especialidad, other.especialidad);
	}
	
}
