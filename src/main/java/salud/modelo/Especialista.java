package salud.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "especialistas")
public class Especialista extends Sanitario {
	
	// Atributos

	private String especialidad;
	private Map<RolEstudio, Collection<Estudio>> estudios;
	private Collection<PlantillaFormulario> plantillas;
	private Collection<Consulta> consultas;
	
	// Constructores
	
	public Especialista(String nombre, String apellido1, String apellido2, String email, String telefono,
			String nCol, String especialidad) {
		super(nombre, apellido1, apellido2, email, telefono, nCol);
		this.especialidad = especialidad;
		this.estudios = new HashMap<RolEstudio, Collection<Estudio>>();
		this.plantillas = new LinkedList<PlantillaFormulario>();
		this.consultas = new LinkedList<Consulta>();
	}
	
	// Métodos

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public Map<RolEstudio, Collection<Estudio>> getEstudios() {
		return estudios;
	}

	public void setEstudios(Map<RolEstudio, Collection<Estudio>> estudios) {
		this.estudios = estudios;
	}
	
	public void agregarEstudios(Map<RolEstudio, Collection<Estudio>> estudios) {
		this.estudios.putAll(estudios);
	}
	
	public void eliminarEstudios(Collection<Estudio> estudios) {
		for (Estudio estudio : estudios) {
			for (RolEstudio rol : RolEstudio.values()) {
				this.estudios.remove(rol, estudio);
			}
		}
	}
	
	public Collection<PlantillaFormulario> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(Collection<PlantillaFormulario> plantillas) {
		this.plantillas = plantillas;
	}
	
	public void agregarPlantillas(Collection<PlantillaFormulario> plantillas) {
		for (PlantillaFormulario plantillaFormulario : plantillas) {
			if (!this.plantillas.contains(plantillaFormulario))
				this.plantillas.add(plantillaFormulario);
		}
	}
	
	public void eliminarPlantillas(Collection<PlantillaFormulario> plantillas) {
		this.plantillas.removeAll(plantillas);
	}

	public Collection<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Collection<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public void agregarConsultas(Collection<Consulta> consultas) {
		for (Consulta consulta : consultas) {
			if (!this.consultas.contains(consulta))
				this.consultas.add(consulta);
		}
	}
	
	public void responderConsulta(Consulta consulta, Respuesta respuesta) {
		if (this.consultas.contains(consulta))
			consulta.setRespuesta(respuesta);
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
