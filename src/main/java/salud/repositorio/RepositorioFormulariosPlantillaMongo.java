package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.PlantillaFormulario;

public interface RepositorioFormulariosPlantillaMongo extends RepositorioFormulariosPlantilla, MongoRepository<PlantillaFormulario, String> {

}
