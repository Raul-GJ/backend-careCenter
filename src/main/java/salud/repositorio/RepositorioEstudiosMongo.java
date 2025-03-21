package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Estudio;

public interface RepositorioEstudiosMongo extends RepositorioEstudios, MongoRepository<Estudio, String> {

}
