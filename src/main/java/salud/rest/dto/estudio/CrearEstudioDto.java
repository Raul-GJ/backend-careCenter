package salud.rest.dto.estudio;

import javax.validation.constraints.NotEmpty;

public class CrearEstudioDto {

	// Atributos
	
	@NotEmpty(message = "El campo 'nombre' no debe estar vacío")
	private String nombre;

	@NotEmpty(message = "El campo 'descripcion' no debe estar vacío")
	private String descripcion;
	
	@NotEmpty(message = "El campo 'fechaAlta' no debe estar vacío")
	private String fechaAlta;
	
	@NotEmpty(message = "El campo 'fechaFin' no debe estar vacío")
	private String fechaFin;
	
	@NotEmpty(message = "El campo 'creador' no debe estar vacío")
	private String creador;
	
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

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}
}
