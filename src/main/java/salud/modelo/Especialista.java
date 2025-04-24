package salud.modelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Especialista extends Sanitario {
	
	// Atributos

	private String especialidad;
	@DBRef
	private Collection<Plantilla> plantillas;
	
	// Constructores
	
	public Especialista(String nombre, String apellidos, String email, String telefono,
			String nCol, String especialidad) {
		super(nombre, apellidos, email, telefono, TipoUsuario.ESPECIALISTA, nCol);
		this.especialidad = especialidad;
		this.plantillas = new LinkedList<Plantilla>();
	}
	
	// Métodos

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public Collection<Plantilla> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(Collection<Plantilla> plantillas) {
		this.plantillas = plantillas;
	}
	
	public void agregarPlantilla(Plantilla plantilla) {
		if (!this.plantillas.contains(plantilla))
			this.plantillas.add(plantilla);
	}
	
	public void agregarPlantillas(Collection<Plantilla> plantillas) {
		for (Plantilla plantillaFormulario : plantillas) {
			agregarPlantilla(plantillaFormulario);
		}
	}
	
	public void eliminarPlantillas(Collection<Plantilla> plantillas) {
		this.plantillas.removeAll(plantillas);
	}
	
	public void eliminarPlantilla(Plantilla plantilla) {
		this.plantillas.remove(plantilla);
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
