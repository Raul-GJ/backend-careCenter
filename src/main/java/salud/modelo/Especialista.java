package salud.modelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "especialistas")
public class Especialista extends Sanitario {
	
	// Atributos

	private String especialidad;
	@DBRef
	private Collection<InfoEstudio> estudios;
	@DBRef
	private Collection<PlantillaFormulario> plantillas;
	
	// Constructores
	
	public Especialista(String nombre, String apellido1, String apellido2, String email, String telefono,
			String nCol, String especialidad) {
		super(nombre, apellido1, apellido2, email, telefono, nCol);
		this.especialidad = especialidad;
		this.estudios = new LinkedList<InfoEstudio>();
		this.plantillas = new LinkedList<PlantillaFormulario>();
	}
	
	// Métodos

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public Collection<InfoEstudio> getEstudios() {
		return estudios;
	}

	public void setEstudios(Collection<InfoEstudio> estudios) {
		this.estudios = estudios;
	}
	
	public void agregarEstudios(Collection<InfoEstudio> estudios) {
		this.estudios.addAll(estudios);
	}
	
	public void eliminarEstudios(Collection<Estudio> estudios) {
		this.estudios = this.estudios.stream().filter(ie -> 
			!estudios.contains(ie.getEstudio())).collect(Collectors.toList());
	}
	
	public void agregarEstudio(InfoEstudio estudio) {
		this.estudios.add(estudio);
	}
	
	public void eliminarEstudio(Estudio estudio) {
		this.estudios = this.estudios.stream().filter(ie -> 
			!ie.getEstudio().getId().equals(estudio.getId())).collect(Collectors.toList());
	}
	
	public void setRol(Estudio estudio, RolEstudio nuevoRol) {
		for (InfoEstudio infoEstudio : this.estudios) {
			if (infoEstudio.getEstudio().equals(estudio))
				infoEstudio.setRol(nuevoRol);
		}
	}
	
	public Collection<PlantillaFormulario> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(Collection<PlantillaFormulario> plantillas) {
		this.plantillas = plantillas;
	}
	
	public void agregarPlantilla(PlantillaFormulario plantilla) {
		if (!this.plantillas.contains(plantilla))
			this.plantillas.add(plantilla);
	}
	
	public void agregarPlantillas(Collection<PlantillaFormulario> plantillas) {
		for (PlantillaFormulario plantillaFormulario : plantillas) {
			agregarPlantilla(plantillaFormulario);
		}
	}
	
	public void eliminarPlantillas(Collection<PlantillaFormulario> plantillas) {
		this.plantillas.removeAll(plantillas);
	}
	
	public void eliminarPlantilla(PlantillaFormulario plantilla) {
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
