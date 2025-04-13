package salud.modelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

public abstract class Sanitario extends Usuario {
	
	// Atributos
	
	private String nCol;
	@DBRef
	private List<Paciente> pacientes;
	@DBRef
	private Collection<Consulta> consultas;
	
	// Constructores
	
	public Sanitario(String nombre, String apellido1, String apellido2, String email, String telefono,
			String nCol) {
		super(nombre, apellido1, apellido2, email, telefono);
		this.nCol = nCol;
		this.pacientes = new LinkedList<Paciente>();
		this.consultas = new LinkedList<Consulta>();
	}
	
	// Métodos
	
	public String getNCol() {
		return nCol;
	}

	public void setNCol(String nCol) {
		this.nCol = nCol;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
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
	
	public Collection<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Collection<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public void agregarConsulta(Consulta consulta) {
	if (!this.consultas.contains(consulta))
		this.consultas.add(consulta);
	}
	
	public void agregarConsultas(Collection<Consulta> consultas) {
		for (Consulta consulta : consultas) {
			agregarConsulta(consulta);
		}
	}
	
	public void responderConsulta(Consulta consulta, Respuesta respuesta) {
		if (this.consultas.contains(consulta))
			consulta.setRespuesta(respuesta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nCol);
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
		return Objects.equals(nCol, other.nCol);
	}
}
