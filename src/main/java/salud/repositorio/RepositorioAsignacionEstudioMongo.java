package salud.repositorio;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.AsignacionEstudio;

public interface RepositorioAsignacionEstudioMongo extends RepositorioAsignacionEstudio, MongoRepository<AsignacionEstudio, String> {
	
	public Collection<AsignacionEstudio> findByEspecialista_Id(String especialistaId);
	
	public Collection<AsignacionEstudio> findByEstudio_Id(String estudioId);
}
