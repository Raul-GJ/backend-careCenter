	package salud.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seguimientos")
public class Seguimiento {
	
	// Atributos
	
	@Id
	private String id;
	private LocalDateTime fecha;
	private LocalDateTime plazo;
	private Formulario formulario;
	private String motivo;
	
	// Constructores
	
	public Seguimiento(LocalDateTime fecha, LocalDateTime plazo, Formulario formulario, String motivo) {
		super();
		this.fecha = fecha;
		this.plazo = plazo;
		this.formulario = formulario;
		this.motivo = motivo;
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

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha, formulario, id, plazo, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguimiento other = (Seguimiento) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(formulario, other.formulario)
				&& Objects.equals(id, other.id) && Objects.equals(plazo, other.plazo)
				&& Objects.equals(motivo, other.motivo);
	}

	@Override
	public String toString() {
		return "Seguimiento [id=" + id + ", fecha=" + fecha + ", plazo=" + plazo + ", formulario=" + formulario
				+ ", motivo=" + motivo + "]";
	}

	
}
