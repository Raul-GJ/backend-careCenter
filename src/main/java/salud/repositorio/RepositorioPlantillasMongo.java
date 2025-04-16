package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Plantilla;

public interface RepositorioPlantillasMongo extends RepositorioPlantillas, MongoRepository<Plantilla, String> {

}
