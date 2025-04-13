package salud.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaFin;
	@DBRef
	private Collection<Paciente> pacientes;
	@DBRef
	private Collection<Seguimiento> seguimientos;
	@DBRef
	private Collection<Alerta> alertas;
	@DBRef
	private Map<Especialista, RolEstudio> especialistas;
	
	// Constructores
	
	public Estudio(String nombre, String descripcion, LocalDateTime fechaAlta, LocalDateTime fechaFin, 
			Especialista creador) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaFin = fechaFin;
		this.pacientes = new LinkedList<Paciente>();
		this.seguimientos = new LinkedList<Seguimiento>();
		this.alertas = new LinkedList<Alerta>();
		this.especialistas = new HashMap<Especialista, RolEstudio>();
		this.especialistas.put(creador, RolEstudio.CREADOR);
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
	
	public Map<Especialista, RolEstudio> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Map<Especialista, RolEstudio> especialistas) {
		this.especialistas = especialistas;
	}
	
	public void agregarEspecialistas(Map<Especialista, RolEstudio> especialistas) {
		this.especialistas.putAll(especialistas);
	}
	
	public void agregarEspecialista(Especialista especialista, RolEstudio rol) {
		this.especialistas.put(especialista, rol);
	}
	
	public void eliminarEspecialistas(Collection<Especialista> especialistas) {
		especialistas.forEach(e -> this.especialistas.remove(e));
	}
	
	public void eliminarEspecialista(Especialista especialista) {
		this.especialistas.remove(especialista);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alertas, descripcion, fechaAlta, fechaFin, id, nombre, pacientes,
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
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pacientes, other.pacientes) && Objects.equals(seguimientos, other.seguimientos);
	}
}
