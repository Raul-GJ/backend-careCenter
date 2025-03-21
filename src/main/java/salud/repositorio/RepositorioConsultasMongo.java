package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Consulta;

public interface RepositorioConsultasMongo extends RepositorioConsultas, MongoRepository<Consulta, String> {

}
