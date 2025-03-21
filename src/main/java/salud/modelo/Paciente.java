package salud.modelo;

import java.util.List;
import java.util.LinkedList;

public class Paciente extends Usuario {
	
	// Atributos
	
	private MedicoFamilia medicoCabecera;
	private List<Alerta> alertas;
	private List<Consulta> consultas;
	private List<Especialista> especialistas;
	private List<Seguimiento> seguimientos;
	private List<Formulario> formularios;
	
	// Constructores

	public Paciente(String nombre, String apellido1, String apellido2, String email, 
			String telefono, MedicoFamilia medicoCabecera) {
		super(telefono, telefono, telefono, telefono, telefono);
		this.setMedicoCabecera(medicoCabecera);
		this.alertas = new LinkedList<Alerta>();
		this.consultas = new LinkedList<Consulta>();
		this.especialistas = new LinkedList<Especialista>();
		this.seguimientos = new LinkedList<Seguimiento>();
		this.formularios = new LinkedList<Formulario>();
	}
	
	// Métodos

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	public void addAlerta(Alerta alerta) {
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
		this.seguimientos.add(seguimiento);
	}
	
	public void removeSeguimiento(Seguimiento seguimiento) {
		this.seguimientos.remove(seguimiento);
	}
	
	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}
	
	public Formulario getFormulario(int pos) {
		return this.formularios.get(pos);
	}
	
	public void addFormulario(Formulario formulario) {
		this.formularios.add(formulario);
	}
	
	public void removeFormulario(int pos) {
		this.formularios.remove(pos);
	}
	
}
