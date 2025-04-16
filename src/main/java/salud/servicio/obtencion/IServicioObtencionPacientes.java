package salud.servicio.obtencion;

import java.util.Collection;

import salud.modelo.Paciente;
import salud.rest.excepciones.EntidadNoEncontrada;

public interface IServicioObtencionPacientes {
	
	public Paciente obtenerPaciente(String id) throws EntidadNoEncontrada;
	
	public Collection<Paciente> obtenerPacientes(Collection<String> ids);
	
	public Collection<Paciente> obtenerPacientes();
}
