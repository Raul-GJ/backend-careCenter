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
	private List<PreguntaEncuesta> datos;
	
	// Constructores

	public PlantillaFormulario(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.publico = true;
		this.datos = new LinkedList<PreguntaEncuesta>();
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

	public List<PreguntaEncuesta> getDatos() {
		return datos;
	}

	public void setDatos(List<PreguntaEncuesta> datos) {
		this.datos = datos;
	}
	
	public int addDato(PreguntaEncuesta dato) {
		this.datos.add(dato);
		return this.datos.size();
	}
	
	public void removeDato(int pos) {
		this.datos.remove(pos);
	}
	
	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}
}
