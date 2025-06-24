package salud.repositorio;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Alerta;

public interface RepositorioAlertasMongo extends RepositorioAlertas, MongoRepository<Alerta, String> {

	public Collection<Alerta> findByEmisor(String emisor);
}
