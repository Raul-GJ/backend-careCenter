package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Alerta;

public interface RepositorioAlertasMongo extends RepositorioAlertas, MongoRepository<Alerta, String> {

}
