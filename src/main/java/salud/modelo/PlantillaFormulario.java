package salud.modelo;

import java.util.List;
import java.util.LinkedList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import salud.modelo.encuesta.PreguntaEncuesta;

@Document(collection = "formularios")
public class PlantillaFormulario {
	
	// Atributos
	
	@Id
	private String id;
	private String nombre;
	private String descripcion;
	private boolean publico;
	private List<PreguntaEncuesta> preguntas;
	
	// Constructores

	public PlantillaFormulario(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.publico = true;
		this.preguntas = new LinkedList<PreguntaEncuesta>();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public List<PreguntaEncuesta> getPreguntas() {
		return preguntas;
	}
	
	public PreguntaEncuesta getPregunta(int pos) {
		return preguntas.get(pos);
	}

	public void setPreguntas(List<PreguntaEncuesta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public int addPregunta(PreguntaEncuesta dato) {
		this.preguntas.add(dato);
		return this.preguntas.size();
	}
	
	public void removePregunta(int pos) {
		this.preguntas.remove(pos);
	}
}
