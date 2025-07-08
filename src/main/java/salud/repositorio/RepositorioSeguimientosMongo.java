package salud.repositorio;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Seguimiento;

public interface RepositorioSeguimientosMongo extends RepositorioSeguimientos, MongoRepository<Seguimiento, String> {
	
	public Collection<Seguimiento> findByIdGrupo(String idGrupo);
}
