package salud.modelo;

public class NotaPaciente {
	
	// Atributos
	
	private Sanitario sanitario;
	private String asunto;
	private String nota;
	private boolean privado; // True = El paciente la puede ver, False = El paciente no la puede ver (otros sanitarios si)
	
	// Constructores
	
	public NotaPaciente(Sanitario sanitario, String asunto, String nota, boolean privado) {
		super();
		this.sanitario = sanitario;
		this.asunto = asunto;
		this.nota = nota;
		this.privado = privado;
	}
	
	// Métodos
	
	public Sanitario getSanitario() {
		return sanitario;
	}
	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}
	
	
}
