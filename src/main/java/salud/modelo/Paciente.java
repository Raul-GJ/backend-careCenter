package salud.modelo;

import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document(collection = "pacientes")
public class Paciente extends Usuario {
	
	// Atributos
	
	private MedicoFamilia medicoCabecera;
	private List<Alerta> alertas;
	private List<Consulta> consultas;
	private List<Especialista> especialistas;
	private List<Seguimiento> seguimientos;
	
	// Constructores

	public Paciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, MedicoFamilia medicoCabecera) {
		super(nombre, apellido1, apellido2, email, telefono);
		this.setMedicoCabecera(medicoCabecera);
		this.alertas = new LinkedList<Alerta>();
		this.consultas = new LinkedList<Consulta>();
		this.especialistas = new LinkedList<Especialista>();
		this.seguimientos = new LinkedList<Seguimiento>();
	}
	
	// Métodos

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	public void addAlerta(Alerta alerta) {
		if (!this.alertas.contains(alerta))
			this.alertas.add(alerta);
	}
	
	public void removeAlerta(Alerta alerta) {
		this.alertas.remove(alerta);
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public void addConsulta(Consulta consulta) {
		if (!this.consultas.contains(consulta))
			this.consultas.add(consulta);
	}
	
	public void addRespuesta(Consulta consulta, Respuesta respuesta) {
		if (this.consultas.contains(consulta))
			consulta.setRespuesta(respuesta);
	}
	
	public Consulta getConsulta(int pos) {
		return this.consultas.get(pos);
	}

	public List<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(List<Especialista> especialistas) {
		this.especialistas = especialistas;
	}
	
	public Especialista getEspecialista(int pos) {
		return this.especialistas.get(pos);
	}
	
	public void addEspecialista(Especialista especialista) {
		if (!this.especialistas.contains(especialista))
			this.especialistas.add(especialista);
	}
	
	public void removeEspecialista(Especialista especialista) {
		this.especialistas.remove(especialista);
	}

	public MedicoFamilia getMedicoCabecera() {
		return medicoCabecera;
	}

	public void setMedicoCabecera(MedicoFamilia medicoCabecera) {
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
	
	public void addSeguimiento(Seguimiento seguimiento) {
		if (!this.seguimientos.contains(seguimiento))
			this.seguimientos.add(seguimiento);
	}
	
	public void removeSeguimiento(Seguimiento seguimiento) {
		this.seguimientos.remove(seguimiento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(alertas, consultas, especialistas, medicoCabecera, seguimientos);
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
		return Objects.equals(alertas, other.alertas) && Objects.equals(consultas, other.consultas)
				&& Objects.equals(especialistas, other.especialistas)
				&& Objects.equals(medicoCabecera, other.medicoCabecera)
				&& Objects.equals(seguimientos, other.seguimientos);
	}
	
}
