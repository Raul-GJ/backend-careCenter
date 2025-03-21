package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Especialista;

public interface RepositorioEspecialistasMongo extends RepositorioEspecialistas, MongoRepository<Especialista, String> {

}
