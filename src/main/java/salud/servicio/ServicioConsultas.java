package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Paciente;
import salud.modelo.Respuesta;
import salud.modelo.Sanitario;
import salud.modelo.Usuario;
import salud.repositorio.RepositorioConsultas;
import salud.rest.excepciones.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioConsultas implements IServicioConsultas {
	
	// Atributos
	
	private RepositorioConsultas repositorioConsultas;
	private IServicioAlertas servicioAlertas;
	private IServicioUsuarios servicioUsuarios;
	
	// Constructores
	
	public ServicioConsultas(RepositorioConsultas repositorioConsultas, IServicioAlertas servicioAlertas,
			IServicioUsuarios servicioUsuarios) {
		super();
		this.repositorioConsultas = repositorioConsultas;
		this.servicioAlertas = servicioAlertas;
		this.servicioUsuarios = servicioUsuarios;
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
		
		Usuario usrPaciente = servicioUsuarios.obtenerUsuarioPorId(emisor);
		Usuario usrSanitario = servicioUsuarios.obtenerUsuarioPorId(receptor);
		
		if (!(usrPaciente instanceof Paciente))
			throw new EntidadNoEncontrada("No existe el paciente con id " + emisor);
		Paciente paciente = (Paciente) usrPaciente;
		
		if (!(usrSanitario instanceof Sanitario))
			throw new EntidadNoEncontrada("No existe el sanitario con id " + receptor);
		Sanitario sanitario = (Sanitario) usrSanitario;
		
		Consulta consulta = new Consulta(asunto, mensaje, paciente, sanitario);
		String idConsulta = repositorioConsultas.save(consulta).getId();
		
		Alerta alertaTmp = generarAlertaConsulta(consulta);
		servicioAlertas.altaAlerta(emisor, receptor, true, alertaTmp.getAsunto(), 
				alertaTmp.getMensaje(), alertaTmp.getFecha());
		
		return idConsulta;
	}

	@Override
	public void eliminarConsulta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		repositorioConsultas.deleteById(id);
	}
	
	private Alerta generarAlertaRespuesta(Consulta consulta) {
		Usuario receptor = consulta.getReceptor();
		Usuario emisor = consulta.getEmisor();
		String asunto = receptor.getNombre() + " " + receptor.getApellidos() + 
				" ha respondido a tu consulta";
		String mensaje = "Hola " + 
				emisor.getNombre() + " " + emisor.getApellidos() + 
				", tu consulta ha sido respondida, revisa tu buzón de consultas";
		
		return new Alerta(emisor, receptor, true, asunto, mensaje, LocalDateTime.now());
	}
	
	private Alerta generarAlertaConsulta(Consulta consulta) {
		Usuario receptor = consulta.getReceptor();
		Usuario emisor = consulta.getReceptor();
		String asunto = "Tienes una nueva consulta";
		String mensaje = "Hola " + 
				receptor.getNombre() + " " + receptor.getApellidos() + 
				", tienes una nueva consulta de " + 
				emisor.getNombre() + " " + emisor.getApellidos() + 
				", revisa tu buzón de consultas";
		
		return new Alerta(emisor, receptor, true, asunto, mensaje, LocalDateTime.now());
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
		servicioAlertas.altaAlerta(consulta.getEmisor().getId(), consulta.getReceptor().getId(), 
				true, alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha());
	}

	@Override
	public Consulta obtenerConsulta(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		
		Optional<Consulta> optional = repositorioConsultas.findById(id);
		if (optional.isEmpty()) {
			throw new EntidadNoEncontrada(id);
		}
		Consulta consulta = optional.get();
		return consulta;
	}

	@Override
	public Collection<Consulta> obtenerConsultas() {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findAll().forEach(consulta -> consultas.add(consulta));
		return consultas;
	}

	@Override
	public Collection<Consulta> obtenerConsultasUsuario(String id) throws EntidadNoEncontrada {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		Collection<Consulta> consultas;
		if (usuario instanceof Paciente)
			consultas = repositorioConsultas.findByEmisor(id);
		else
			consultas = repositorioConsultas.findByReceptor(id);
		return consultas;
	}
}
