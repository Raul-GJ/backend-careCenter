package salud.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seguimientos")
public class Seguimiento {
	
	// Atributos
	
	@Id
	private String id;
	private LocalDateTime fecha;
	private LocalDateTime plazo;
	private PlantillaFormulario formulario;
	
	// Constructores
	
	public Seguimiento(LocalDateTime fecha, LocalDateTime plazo, PlantillaFormulario formulario) {
		super();
		this.fecha = fecha;
		this.plazo = plazo;
		this.formulario = formulario;
	}
	
	// Métodos

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getPlazo() {
		return plazo;
	}

	public void setPlazo(LocalDateTime plazo) {
		this.plazo = plazo;
	}

	public PlantillaFormulario getFormulario() {
		return formulario;
	}

	public void setFormulario(PlantillaFormulario formulario) {
		this.formulario = formulario;
	}
}
