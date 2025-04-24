package salud.rest;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import salud.auth.JwtUtils;
import salud.rest.dto.auth.LoginDto;
import salud.rest.dto.usuario.CrearEspecialistaDto;
import salud.rest.dto.usuario.CrearMedicoDto;
import salud.rest.dto.usuario.CrearPacienteDto;
import salud.servicio.IServicioEspecialistas;
import salud.servicio.IServicioMedicos;
import salud.servicio.IServicioPacientes;

@RestController
@RequestMapping("/salud/api/auth")
public class ControladorAuth implements AuthApi {

	// Atributos
	
	private IServicioEspecialistas servicioEspecialistas;
	private IServicioMedicos servicioMedicos;
	private IServicioPacientes servicioPacientes;
	
	// Constructores
	
	public ControladorAuth(IServicioEspecialistas servicioEspecialistas, IServicioMedicos servicioMedicos,
			IServicioPacientes servicioPacientes) {
		super();
		this.servicioEspecialistas = servicioEspecialistas;
		this.servicioMedicos = servicioMedicos;
		this.servicioPacientes = servicioPacientes;
	}
	
	// Métodos

	@Override
	public ResponseEntity<Map<String, Object>> login(LoginDto dto, HttpServletResponse response) {
		String correo = dto.getCorreo();
		String contrasenya = dto.getContrasenya();
		
		Map<String, Object> claims = verificarCredenciales(correo, contrasenya);
		if (claims != null) {

			String token = JwtUtils.generateToken(claims);
			System.out.println("Token: " + token);

			// Enviar el token en una cookie HttpOnly
			Cookie cookie = new Cookie("jwt", token);
			cookie.setMaxAge(3600);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);

			Map<String, Object> jsonResponse = new HashMap<>();
			jsonResponse.put("token", token);
			jsonResponse.put("id", correo);
			// El nombre completo se puede extraer de un repositorio o
			jsonResponse.put("fullName", claims.get("fullName"));
			jsonResponse.put("roles", claims.get("roles"));

			return ResponseEntity.ok(jsonResponse);

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("message", "Credenciales inválidas"));

		}
	}
	
	@Override
	public ResponseEntity<Void> altaEspecialista(@Valid CrearEspecialistaDto especialistaDto)
			throws Exception {
		String id = servicioEspecialistas.altaEspecialista(especialistaDto.getNombre(), 
				especialistaDto.getApellidos(), 
				especialistaDto.getEmail(), 
				especialistaDto.getTelefono(),
				especialistaDto.getContrasenya(),
				especialistaDto.getnCol(),
				especialistaDto.getEspecialidad());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> altaMedico(@Valid CrearMedicoDto medicoDto) {
		String id = servicioMedicos.altaMedico(medicoDto.getNombre(), 
				medicoDto.getApellidos(), 
				medicoDto.getEmail(), 
				medicoDto.getTelefono(),
				medicoDto.getContrasenya(),
				medicoDto.getnCol(),
				medicoDto.getAtributoTemporal());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<Void> altaPaciente(@Valid CrearPacienteDto pacienteDto) throws Exception {
		String id = servicioPacientes.altaPaciente(pacienteDto.getNombre(), 
				pacienteDto.getApellidos(), 
				pacienteDto.getEmail(), 
				pacienteDto.getTelefono(),
				pacienteDto.getContrasenya(),
				pacienteDto.getMedicoCabecera());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id).buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request, 
			HttpServletResponse response) {
		// Obtener el token actual para verificar que existe
		Cookie[] cookies = request.getCookies();
		String token = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("jwt".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}

		// Crear una cookie con el mismo nombre pero con tiempo de vida 0 para
		// eliminarla
		Cookie cookie = new Cookie("jwt", "");
		cookie.setMaxAge(0); // Esto hace que la cookie expire inmediatamente
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setSecure(true); // Solo enviar por HTTPS
		response.addCookie(cookie);

		// También podemos limpiar cualquier otro dato de sesión si es necesario

		Map<String, Object> jsonResponse = new HashMap<>();

		if (token != null) {
			// Si había un token, confirmamos que se ha eliminado
			jsonResponse.put("message", "Sesión cerrada correctamente");
			jsonResponse.put("success", true);
		} else {
			// Si no había token, informamos que no había sesión activa
			jsonResponse.put("message", "No había sesión activa");
			jsonResponse.put("success", true);
		}

		return ResponseEntity.ok(jsonResponse);
	}
	
	private Map<String, Object> verificarCredenciales(String username, String password) {

	    if ("admin@ejemplo.com".equals(username) && "admin".equals(password)) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("sub", username);
	        claims.put("roles", "ADMIN,ESPECIALISTA,MEDICO,SANITARIO,PACIENTE,USUARIO");
	        claims.put("fullName", "Administrador del Sistema");
	        return claims;
	    } else if ("paciente@ejemplo.com".equals(username) && "paciente".equals(password)) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("sub", username);
	        claims.put("roles", "PACIENTE,USUARIO");
	        claims.put("fullName", "Paciente");
	        return claims;
	    } else if ("medico@ejemplo.com".equals(username) && "medico".equals(password)) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("sub", username);
	        claims.put("roles", "MEDICO,SANITARIO,USUARIO");
	        claims.put("fullName", "Medico de familia");
	        return claims;
	    } else if ("especialista@ejemplo.com".equals(username) && "especialista".equals(password)) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("sub", username);
	        claims.put("roles", "ESPECIALISTA,SANITARIO,USUARIO");
	        claims.put("fullName", "Especialista");
	        return claims;
	    } else {
	        return null;
	    }
	}
}
