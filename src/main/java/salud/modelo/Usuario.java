package salud.modelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public abstract class Usuario {
	
	// Atributos
	
	@Id
	private String id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String telefono;
	@DBRef
	private List<Alerta> alertas;
	boolean eliminado;
	
	// Constructores
	
	public Usuario(String nombre, String apellido1, String apellido2, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.alertas = new LinkedList<Alerta>();
		this.eliminado = false;
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	public void agregarAlertas(Collection<Alerta> alertas) {
		for (Alerta alerta : alertas) {
			agregarAlerta(alerta);
		}
	}
	
	public void agregarAlerta(Alerta alerta) {
		if (!this.alertas.contains(alerta))
			this.alertas.add(alerta);
	}
	
	public void eliminarAlertas(Collection<Alerta> alertas) {
		this.alertas.removeAll(alertas);
	}
	
	public void eliminarAlerta(Alerta alerta) {
		this.alertas.remove(alerta);
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alertas, apellido1, apellido2, email, id, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(alertas, other.alertas) && Objects.equals(apellido1, other.apellido1)
				&& Objects.equals(apellido2, other.apellido2)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	}
}
