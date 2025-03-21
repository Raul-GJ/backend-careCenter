package salud.rest.dto.formulario;

import salud.modelo.PlantillaFormulario;

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
	
	public static CrearPlantillaFormularioDto from(PlantillaFormulario formulario) {
		CrearPlantillaFormularioDto formularioDto = new CrearPlantillaFormularioDto();	
		formularioDto.setNombre(formulario.getNombre());
		formularioDto.setDescripcion(formulario.getDescripcion());
		
		return formularioDto;
	}
}
