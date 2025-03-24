package salud.rest.dto.formulario;

public class CrearPlantillaFormularioDto {
	
	// Atributos
	
	private String nombre;
	private String descripcion;
	
	// Constructores
	
	public CrearPlantillaFormularioDto() {
		
	}
	
	// Métodos

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
}
