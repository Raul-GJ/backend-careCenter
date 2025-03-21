package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.MedicoFamilia;

public interface RepositorioMedicosMongo extends RepositorioMedicos, MongoRepository<MedicoFamilia, String> {

}
