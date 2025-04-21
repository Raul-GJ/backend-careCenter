package salud.modelo;

import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Collection;
import java.util.LinkedList;

public class Paciente extends Usuario {
	
	// Atributos
	
	@DBRef
	private Medico medicoCabecera;
	@DBRef
	private List<Especialista> especialistas;
	@DBRef
	private List<Seguimiento> seguimientos;
	
	// Constructores

	public Paciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, Medico medicoCabecera) {
		super(nombre, apellido1, apellido2, email, telefono, TipoUsuario.PACIENTE);
		this.setMedicoCabecera(medicoCabecera);
		this.especialistas = new LinkedList<Especialista>();
		this.seguimientos = new LinkedList<Seguimiento>();
	}
	
	// Métodos

	public List<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(List<Especialista> especialistas) {
		this.especialistas = especialistas;
	}
	
	public Especialista getEspecialista(int pos) {
		return this.especialistas.get(pos);
	}
	
	public void agregarEspecialistas(Collection<Especialista> especialistas) {
		for (Especialista especialista : especialistas) {
			agregarEspecialista(especialista);
		}
	}
	
	public void agregarEspecialista(Especialista especialista) {
		if (!this.especialistas.contains(especialista))
			this.especialistas.add(especialista);
	}
	
	public void eliminarEspecialistas(Collection<Especialista> especialistas) {
		this.especialistas.removeAll(especialistas);
	}
	
	public void eliminarEspecialista(Especialista especialista) {
		this.especialistas.remove(especialista);
	}

	public Medico getMedicoCabecera() {
		return medicoCabecera;
	}

	public void setMedicoCabecera(Medico medicoCabecera) {
		this.medicoCabecera = medicoCabecera;
	}
	
	public List<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}
	
	public Seguimiento getSeguimiento(int pos) {
		return this.seguimientos.get(pos);
	}
	
	public void agregarSeguimientos(Collection<Seguimiento> seguimientos) {
		for (Seguimiento seguimiento : seguimientos) {
			agregarSeguimiento(seguimiento);
		}
	}
	
	public void agregarSeguimiento(Seguimiento seguimiento) {
		if (!this.seguimientos.contains(seguimiento))
			this.seguimientos.add(seguimiento);
	}
	
	public void eliminarSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos.removeAll(seguimientos);
	}
	
	public void eliminarSeguimiento(Seguimiento seguimiento) {
		this.seguimientos.remove(seguimiento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(seguimientos);
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
		Paciente other = (Paciente) obj;
		return Objects.equals(seguimientos, other.seguimientos);
	}
	
}
