package salud.modelo;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.Id;
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
	private LocalDate fechaNacimiento;
	private String sexo;
	private String direccion;
	private String dni;
	private TipoUsuario tipo;
	boolean eliminado;
	private String contrasenya;
	
	// Constructores
	
	public Usuario(String nombre, String apellidos, String email, String telefono, LocalDate fechaNacimiento,
			String sexo, String direccion, String dni, TipoUsuario tipo, String contrasenya) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.direccion = direccion;
		this.dni = dni;
		this.tipo = tipo;
		this.eliminado = false;
		this.contrasenya = contrasenya;
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
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
		return Objects.hash(apellidos, contrasenya, direccion, dni, eliminado, email, fechaNacimiento, id,
				nombre, sexo, telefono, tipo);
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
		return Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(contrasenya, other.contrasenya) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(dni, other.dni) && eliminado == other.eliminado && Objects.equals(email, other.email)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sexo, other.sexo)
				&& Objects.equals(telefono, other.telefono) && tipo == other.tipo;
	}
}
