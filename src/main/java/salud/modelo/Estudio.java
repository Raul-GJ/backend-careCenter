package salud.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estudios")
public class Estudio {
	
	// Atributos
	
	@Id
	private String id;
	private String nombre;
	private String descripcion;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaFin;
	private Especialista creador;
	private Collection<Paciente> pacientes;
	private Collection<Especialista> especialistas;
	private Collection<Seguimiento> seguimientos;
	private Collection<Alerta> alertas;
	
	// Constructores
	
	public Estudio(String nombre, String descripcion, LocalDateTime fechaAlta, LocalDateTime fechaFin,
			Especialista creador) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaFin = fechaFin;
		this.creador = creador;
		this.pacientes = new LinkedList<Paciente>();
		this.especialistas = new LinkedList<Especialista>();
		this.seguimientos = new LinkedList<Seguimiento>();
		this.alertas = new LinkedList<Alerta>();
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

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Especialista getCreador() {
		return creador;
	}

	public void setCreador(Especialista creador) {
		this.creador = creador;
	}

	public Collection<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public void removePacientes(Collection<Paciente> pacientes) {
		this.pacientes.removeAll(pacientes);
	}

	public Collection<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Collection<Especialista> especialistas) {
		this.especialistas = especialistas;
	}
	
	public void removeEspecialistas(Collection<Especialista> especialistas) {
		this.especialistas.removeAll(especialistas);
	}
	
	public Collection<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}
	
	public void removeSeguimientos(Collection<Seguimiento> seguimientos) {
		seguimientos.forEach(a -> System.out.println(a));
		this.seguimientos.forEach(a -> System.out.println(a));
		seguimientos.forEach(a -> System.out.println(a.toString()));
		this.seguimientos.forEach(a -> System.out.println(a.toString()));
		this.seguimientos.removeAll(seguimientos);
	}

	public Collection<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(Collection<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	public void removeAlertas(Collection<Alerta> alertas) {
		this.alertas.removeAll(alertas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alertas, creador, descripcion, especialistas, fechaAlta, fechaFin, id, nombre, pacientes,
				seguimientos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudio other = (Estudio) obj;
		return Objects.equals(alertas, other.alertas) && Objects.equals(creador, other.creador)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(especialistas, other.especialistas)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pacientes, other.pacientes) && Objects.equals(seguimientos, other.seguimientos);
	}
}
