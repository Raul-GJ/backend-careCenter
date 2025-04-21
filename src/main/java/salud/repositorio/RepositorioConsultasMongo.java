package salud.repositorio;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Consulta;

public interface RepositorioConsultasMongo extends RepositorioConsultas, MongoRepository<Consulta, String> {
	
	public Collection<Consulta> findByEmisor(String emisor);
	
	public Collection<Consulta> findByReceptor(String receptor);
}
