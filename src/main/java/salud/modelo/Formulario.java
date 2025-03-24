package salud.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Formulario {
	
	// Atributos
	
	private LocalDateTime fecha;
	private boolean rellenado;
	private PlantillaFormulario plantilla;
	private List<String> respuestas;
	
	// Constructores
	
	public Formulario(LocalDateTime fecha, PlantillaFormulario plantilla) {
		super();
		this.fecha = fecha;
		this.rellenado = false;
		this.plantilla = plantilla;
		this.respuestas = new LinkedList<String>();
	}
	
	// Métodos

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public boolean isRellenado() {
		return rellenado;
	}

	public void setRellenado(boolean rellenado) {
		this.rellenado = rellenado;
	}

	public PlantillaFormulario getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaFormulario plantilla) {
		this.plantilla = plantilla;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public boolean setRespuestas(List<String> respuestas) {
		List<String> res = new LinkedList<String>();
		if (respuestas.size() != plantilla.getPreguntas().size())
			return false;
		for (int pos = 0; pos < respuestas.size(); pos++) {
			String respuesta = respuestas.get(pos);
			if (plantilla.getPregunta(pos).testRespuesta(respuesta)) {
				res.add(pos, respuesta);
			} else {
				return false;
			}
		}
		this.respuestas = res;
		return true;
	}
	
	public boolean setRespuesta(int pos, String respuesta) {
		if (plantilla.getPregunta(pos).testRespuesta(respuesta)) {
			respuestas.set(pos, respuesta);
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, plantilla, rellenado, respuestas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formulario other = (Formulario) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(plantilla, other.plantilla)
				&& rellenado == other.rellenado && Objects.equals(respuestas, other.respuestas);
	}

	@Override
	public String toString() {
		return "Formulario [fecha=" + fecha + ", rellenado=" + rellenado + ", plantilla=" + plantilla + ", respuestas="
				+ respuestas + "]";
	}
}
