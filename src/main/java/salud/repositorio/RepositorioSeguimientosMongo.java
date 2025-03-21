package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Seguimiento;

public interface RepositorioSeguimientosMongo extends RepositorioSeguimientos, MongoRepository<Seguimiento, String> {

}
