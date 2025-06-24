package salud.modelo;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

public class Paciente extends Usuario {
	
	// Atributos
	
	private String nss; // Número de seguridad social
	private Collection<String> alergias;
	private Collection<String> tratamientos;
	@DBRef
	private Collection<NotaPaciente> notas;
	
	@DBRef
	private Medico medicoCabecera;
	@DBRef
	private Collection<Especialista> especialistas;
	@DBRef
	private Collection<Seguimiento> seguimientos;
	
	// Constructores

	public Paciente(String nombre, String apellidos, String email, String telefono, 
			LocalDate fechaNacimiento, String sexo, String direccion, String dni, String nss, 
			String contrasenya) {
		super(nombre, apellidos, email, telefono, fechaNacimiento, sexo, direccion, dni,
				TipoUsuario.PACIENTE, contrasenya);
		this.medicoCabecera = null;
		this.nss = nss;
		this.especialistas = new LinkedList<Especialista>();
		this.seguimientos = new LinkedList<Seguimiento>();
		this.alergias = new LinkedList<String>();
		this.tratamientos = new LinkedList<String>();
		this.notas = new LinkedList<NotaPaciente>();
	}
	
	// Métodos

	public Collection<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Collection<Especialista> especialistas) {
		this.especialistas = especialistas;
	}
	
	public Especialista getEspecialista(String id) {
		for (Especialista especialista : especialistas) {
			if (especialista.getId().equals(id))
				return especialista;
		}
		return null;
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
	
	public Collection<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Collection<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}
	
	public Seguimiento getSeguimiento(String id) {
		for (Seguimiento seguimiento : seguimientos) {
			if (seguimiento.getId().equals(id))
				return seguimiento;
		}
		return null;
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

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Collection<String> getAlergias() {
		return alergias;
	}

	public void setAlergias(Collection<String> alergias) {
		this.alergias = alergias;
	}
	
	public void agregarAlergia(String alergia) {
		if (!this.alergias.contains(alergia))
			this.alergias.add(alergia);
	}
	
	public void agregarAlergias(Collection<String> alergias) {
		for (String alergia : alergias) {
			agregarAlergia(alergia);
		}
	}
	
	public void eliminarAlergia(String alergia) {
		this.alergias.remove(alergia);
	}
	
	public void eliminarAlergias(Collection<String> alergias) {
		for (String alergia : alergias) {
			eliminarAlergia(alergia);
		}
	}

	public Collection<String> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Collection<String> tratamientos) {
		this.tratamientos = tratamientos;
	}
	
	public void agregarTratamiento(String tratamiento) {
		if (!this.tratamientos.contains(tratamiento))
			this.tratamientos.add(tratamiento);
	}
	
	public void agregarTratamientos(Collection<String> tratamientos) {
		for (String tratamiento : tratamientos) {
			agregarTratamiento(tratamiento);
		}
	}
	
	public void eliminarTratamiento(String tratamiento) {
		this.tratamientos.remove(tratamiento);
	}
	
	public void eliminarTratamientos(Collection<String> tratamientos) {
		for (String tratamiento : tratamientos) {
			eliminarTratamiento(tratamiento);
		}
	}
	
	public Collection<NotaPaciente> getNotas() {
		return notas;
	}

	public void setNotas(Collection<NotaPaciente> notas) {
		this.notas = notas;
	}
	
	public void agregarNota(NotaPaciente nota) {
		if (!this.notas.contains(nota))
			this.notas.add(nota);
	}
	
	public void agregarNotas(Collection<NotaPaciente> notas) {
		for (NotaPaciente notaPaciente : notas) {
			agregarNota(notaPaciente);
		}
	}
	
	public void eliminarNota(NotaPaciente nota) {
		this.notas.remove(nota);
	}
	
	public void eliminarNotas(Collection<NotaPaciente> notas) {
		for (NotaPaciente notaPaciente : notas) {
			eliminarNota(notaPaciente);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(alergias, notas, nss, seguimientos, tratamientos);
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
		return Objects.equals(alergias, other.alergias) && Objects.equals(notas, other.notas)
				&& Objects.equals(nss, other.nss) && Objects.equals(seguimientos, other.seguimientos)
				&& Objects.equals(tratamientos, other.tratamientos);
	}
}
