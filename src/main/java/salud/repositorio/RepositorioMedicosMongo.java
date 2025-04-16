package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Medico;

public interface RepositorioMedicosMongo extends RepositorioMedicos, MongoRepository<Medico, String> {

}
