package salud.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estudios")
public class Estudio {
	
	// Atributos
	
	@Id
	private String id;
	private String nombre;
	private String descripcion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	@DBRef
	private Collection<Paciente> pacientes;
	@DBRef
	private Collection<Seguimiento> seguimientos;
	@DBRef
	private Collection<Alerta> alertas;
	
	// Constructores
	
	public Estudio(String nombre, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.pacientes = new LinkedList<Paciente>();
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

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Collection<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public void agregarPacientes(Collection<Paciente> pacientes) {
		this.pacientes.addAll(pacientes);
	}
	
	public void agregarPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
	}
	
	public void eliminarPacientes(Collection<Paciente> pacientes) {
		this.pacientes.removeAll(pacientes);
	}
	
	public void eliminarPaciente(Paciente paciente) {
		this.pacientes.remove(paciente);
	}
	
	public Collection<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}
	
	public void agregarSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos.addAll(seguimientos);
	}
	
	public void agregarSeguimiento(Seguimiento seguimiento) {
		this.seguimientos.add(seguimiento);
	}
	
	public void eliminarSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos.removeAll(seguimientos);
	}
	
	public void eliminarSeguimiento(Seguimiento seguimiento) {
		this.seguimientos.remove(seguimiento);
	}

	public Collection<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(Collection<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	public void agregarAlertas(Collection<Alerta> alertas) {
		this.alertas.addAll(alertas);
	}
	
	public void agregarAlerta(Alerta alerta) {
		this.alertas.add(alerta);
	}
	
	public void eliminarAlertas(Collection<Alerta> alertas) {
		this.alertas.removeAll(alertas);
	}
	
	public void eliminarAlerta(Alerta alerta) {
		this.alertas.remove(alerta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alertas, descripcion, fechaInicio, fechaFin, id, nombre, pacientes,
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
		return Objects.equals(alertas, other.alertas)
				&& Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pacientes, other.pacientes) && Objects.equals(seguimientos, other.seguimientos);
	}
}
