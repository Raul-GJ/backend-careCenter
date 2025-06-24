package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.NotaPaciente;

public interface RepositorioNotasMongo extends RepositorioNotas, MongoRepository<NotaPaciente, String> {

}
