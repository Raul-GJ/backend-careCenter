package salud.servicio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salud.modelo.Alerta;
import salud.modelo.Consulta;
import salud.modelo.Especialista;
import salud.modelo.Paciente;
import salud.modelo.Respuesta;
import salud.modelo.Usuario;
import salud.repositorio.EntidadNoEncontrada;
import salud.repositorio.RepositorioConsultas;

@Service
@Transactional
public class ServicioConsultas implements IServicioConsultas {
	
	// Atributos
	
	private RepositorioConsultas repositorioConsultas;
	private IServicioPacientes servicioPacientes;
	private IServicioEspecialistas servicioEspecialistas;
	private IServicioAlertas servicioAlertas;
	
	// Constructores
	
	public ServicioConsultas(RepositorioConsultas repositorioConsultas, 
			IServicioPacientes repositorioPacientes, IServicioEspecialistas repositorioEspecialistas, 
			IServicioAlertas servicioAlertas) {
		super();
		this.repositorioConsultas = repositorioConsultas;
		this.servicioPacientes = repositorioPacientes;
		this.servicioEspecialistas = repositorioEspecialistas;
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
		servicioAlertas.altaAlerta(alerta.getAsunto(), alerta.getMensaje(), alerta.getFecha());
		
		servicioPacientes.agregarConsulta(paciente.getId(), consulta);
		servicioEspecialistas.agregarConsulta(especialista.getId(), consulta);
		servicioEspecialistas.agregarAlerta(especialista.getId(), alerta);
		
		return idConsulta;
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
	public Collection<Consulta> obtenerConsultas(Collection<String> ids) {
		Collection<Consulta> consultas = new LinkedList<Consulta>();
		repositorioConsultas.findAllById(ids).forEach(consulta -> consultas.add(consulta));
		return consultas;
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
		servicioPacientes.agregarAlerta(consulta.getEmisor().getId(), generarAlertaRespuesta(consulta));
	}
}
