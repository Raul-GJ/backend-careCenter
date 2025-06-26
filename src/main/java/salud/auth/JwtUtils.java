package salud.auth;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import salud.modelo.TipoUsuario;

public class JwtUtils {

    private static final String SECRETO = "secreto";
    private static final long TIEMPO = 86400; // 24 horas

    public static String generateToken(Map<String, Object> claims) {

        Date caducidad = Date.from(Instant.now().plusSeconds(TIEMPO));

        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRETO)
                .setExpiration(caducidad).compact();

        return token;
    }

    public static Claims validateToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRETO)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
    
    public static String getIdUsuario() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	return (String) auth.getPrincipal();
    }
    
    public static boolean isPaciente() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().stream().anyMatch(
				a -> a.getAuthority().equals(TipoUsuario.PACIENTE.toString()));
    }
    
    public static boolean isMedico() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().stream().anyMatch(
				a -> a.getAuthority().equals(TipoUsuario.MEDICO.toString()));
    }
    
    public static boolean isEspecialista() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().stream().anyMatch(
				a -> a.getAuthority().equals(TipoUsuario.ESPECIALISTA.toString()));
    }
    
    public static boolean isAdmin() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().stream().anyMatch(
				a -> a.getAuthority().equals(TipoUsuario.ADMIN.toString()));
    }

}
