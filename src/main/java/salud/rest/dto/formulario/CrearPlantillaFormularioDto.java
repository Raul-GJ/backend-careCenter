package salud.rest.dto.formulario;

import javax.validation.constraints.NotEmpty;

public class CrearPlantillaFormularioDto {
	
	// Atributos
	
	@NotEmpty(message = "El campo 'nombre' no debe estar vacío")
	private String nombre;

	@NotEmpty(message = "El campo 'descripcion' no debe estar vacío")
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
