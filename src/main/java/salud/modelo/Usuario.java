package salud.modelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
	
	// Atributos
	
	@Id
	private String id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	@DBRef
	private List<Alerta> alertas;
	private TipoUsuario tipo;
	boolean eliminado;
	private String contrasenya;
	
	// Constructores
	
	public Usuario(String nombre, String apellidos, String email, String telefono,
			TipoUsuario tipo, String contrasenya) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.alertas = new LinkedList<Alerta>();
		this.tipo = tipo;
		this.eliminado = false;
		this.setContrasenya(contrasenya);
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	
	public TipoUsuario getTipo() {
		return this.tipo;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alertas, apellidos, eliminado, email, id, nombre, telefono, tipo);
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
		return Objects.equals(alertas, other.alertas) && Objects.equals(apellidos, other.apellidos)
				&& eliminado == other.eliminado && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono)
				&& tipo == other.tipo;
	}
}
