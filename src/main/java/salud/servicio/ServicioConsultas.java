package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Respuesta;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioConsultas;
import salud.rest.excepciones.EntidadNoEncontrada;
import salud.servicio.obtencion.IServicioObtencionConsultas;

@Service
@Transactional
public class ServicioConsultas implements IServicioConsultas {
	
	// Atributos
	
	private RepositorioConsultas repositorioConsultas;
	private IServicioObtencionConsultas servicioConsultas;
	private IServicioPacientes servicioPacientes;
	private IServicioEspecialistas servicioEspecialistas;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioConsultas(RepositorioConsultas repositorioConsultas, 
			IServicioObtencionConsultas servicioConsultas,
			IServicioPacientes servicioPacientes, IServicioEspecialistas servicioEspecialistas,
			IServicioAlertas servicioAlertas) {
		super();
		this.repositorioConsultas = repositorioConsultas;
		this.servicioConsultas = servicioConsultas;
		this.servicioPacientes = servicioPacientes;
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioAlertas = servicioAlertas;
	}
	
	// Métodos
	
	@Override
	public String altaConsulta(String asunto, String mensaje, String emisor, String receptor) 
			throws EntidadNoEncontrada {
		if (asunto == null || asunto.isEmpty()) {
			throw new IllegalArgumentException("El asunto no puede ser nulo o vacío");
		}
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		
		Paciente paciente = servicioPacientes.obtenerPaciente(emisor);
		Especialista especialista = servicioEspecialistas.obtenerEspecialista(receptor);
		
		Consulta consulta = new Consulta(asunto, mensaje, paciente, especialista);
		String idConsulta = repositorioConsultas.save(consulta).getId();
		
		Alerta alerta = generarAlertaConsulta(consulta);
		String idAlerta = servicioAlertas.altaAlerta(
				alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha());
		servicioEspecialistas.agregarAlerta(especialista.getId(), servicioAlertas.obtenerAlerta(idAlerta));
		
		return idConsulta;
	}

	@Override
	public void eliminarConsulta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioConsultas.deleteById(id);
	}
	
	private String getNombreCompleto(Usuario usuario) {
		if (usuario.getApellido2() == null)
			return usuario.getNombre() + " " + usuario.getApellido1();
		else 
			return usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2();
	}
	
	private Alerta generarAlertaRespuesta(Consulta consulta) {
		String asunto = getNombreCompleto(consulta.getReceptor()) + 
				" ha respondido a tu consulta";
		String mensaje = "Hola " + 
				getNombreCompleto(consulta.getEmisor()) + 
				", tu consulta ha sido respondida, revisa tu buzón de consultas";
		
		return new Alerta(asunto, mensaje, LocalDateTime.now());
	}
	
	private Alerta generarAlertaConsulta(Consulta consulta) {
		String asunto = "Tienes una nueva consulta";
		String mensaje = "Hola " + 
				getNombreCompleto(consulta.getReceptor()) + 
				", tienes una nueva consulta de " + 
				consulta.getEmisor() + 
				", revisa tu buzón de consultas";
		
		return new Alerta(asunto, mensaje, LocalDateTime.now());
	}

	@Override
	public void responderConsulta(String id, String mensaje) throws EntidadNoEncontrada {
		if (mensaje == null || mensaje.isEmpty()) {
			throw new IllegalArgumentException("El mensaje no puede ser nulo o vacío");
		}
		Respuesta respuesta = new Respuesta(mensaje, LocalDateTime.now());
		Consulta consulta = obtenerConsulta(id);
		consulta.setRespuesta(respuesta);
		
		repositorioConsultas.save(consulta);
		Alerta alerta = generarAlertaRespuesta(consulta);
		String idAlerta = servicioAlertas.altaAlerta(
				alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha());
		servicioPacientes.agregarAlerta(consulta.getEmisor().getId(), 
				servicioAlertas.obtenerAlerta(idAlerta));
	}

	@Override
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada {
		return servicioConsultas.obtenerConsulta(id);
	}

	@Override
	public Collection<Consulta> obtenerConsultas() {
		return servicioConsultas.obtenerConsultas();
	}

	@Override
	public Collection<Consulta> obtenerConsultas(Collection<String> ids) {
		return servicioConsultas.obtenerConsultas(ids);
	}
}
