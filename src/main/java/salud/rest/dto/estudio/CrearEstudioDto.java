package salud.rest.dto.estudio;

import javax.validation.constraints.NotEmpty;

public class CrearEstudioDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'nombre' no debe estar vacío")
	private String nombre;

	@NotEmpty(message = "El campo 'descripcion' no debe estar vacío")
	private String descripcion;
	
	@NotEmpty(message = "El campo 'fechaInicio' no debe estar vacío")
	private String fechaInicio;
	
	@NotEmpty(message = "El campo 'fechaFin' no debe estar vacío")
	private String fechaFin;
	
	// Constructores
	
	public CrearEstudioDto() {

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

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
