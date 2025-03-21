package salud.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

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
	private Collection<Paciente> pacientes;
	private Collection<Especialista> especialistas;
	private Collection<Seguimiento> seguimientos;
	private Collection<Alerta> alertas;
	
	// Constructores
	
	public Estudio(String nombre, String descripcion, LocalDateTime fechaAlta, LocalDateTime fechaFin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaFin = fechaFin;
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

	public Collection<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Collection<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Collection<Especialista> especialistas) {
		this.especialistas = especialistas;
	}
	
	public Collection<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Collection<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(Collection<Alerta> alertas) {
		this.alertas = alertas;
	}
}
