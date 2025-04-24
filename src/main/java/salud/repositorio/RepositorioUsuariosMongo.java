package salud.repositorio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;

public interface RepositorioUsuariosMongo extends RepositorioUsuarios, MongoRepository<Usuario, String> {
	
	public Collection<Usuario> findByTipo(TipoUsuario tipo);
	
	public Optional<Usuario> findByEmail(String email);
}
