package salud.rest.excepciones;

@SuppressWarnings("serial")
public class EntidadNoEncontrada extends Exception {
	
	public EntidadNoEncontrada(String msg) {
		super(msg);		
	}
}
