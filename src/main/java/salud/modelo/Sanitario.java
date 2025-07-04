package salud.modelo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

public abstract class Sanitario extends Usuario {
	
	// Atributos
	
	private String nCol;
	private String centroDeSalud;
	@DBRef
	private Collection<Paciente> pacientes;
	
	// Constructores
	
	public Sanitario(String nombre, String apellidos, String email, String telefono, LocalDate fechaNacimiento,
			String sexo, String direccion, String dni, TipoUsuario tipo, String contrasenya, String nCol, 
			String centroDeSalud) {
		super(nombre, apellidos, email, telefono, fechaNacimiento, sexo, direccion, dni, tipo, contrasenya);
		this.nCol = nCol;
		this.centroDeSalud = centroDeSalud;
		this.pacientes = new LinkedList<Paciente>();
	}
	
	// Métodos
	
	public String getNCol() {
		return nCol;
	}

	public void setNCol(String nCol) {
		this.nCol = nCol;
	}
	
	public String getCentroDeSalud() {
		return centroDeSalud;
	}

	public void setCentroDeSalud(String centroDeSalud) {
		this.centroDeSalud = centroDeSalud;
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
	
	public void eliminarPacientes(Collection<Paciente> pacientes) {
		this.pacientes.removeAll(pacientes);
	}
	
	public void agregarPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
	}
	
	public void eliminarPaciente(Paciente paciente) {
		this.pacientes.remove(paciente);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(centroDeSalud, nCol);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sanitario other = (Sanitario) obj;
		return Objects.equals(centroDeSalud, other.centroDeSalud) && Objects.equals(nCol, other.nCol);
	}
}
