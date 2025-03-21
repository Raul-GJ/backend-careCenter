package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import salud.modelo.Usuario;

public interface RepositorioUsuariosMongo extends RepositorioUsuarios, MongoRepository<Usuario, String> {
	
}
