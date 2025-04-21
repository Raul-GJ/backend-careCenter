package salud.repositorio;

import java.util.Collection;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Consulta;

@NoRepositoryBean
public interface RepositorioConsultas extends PagingAndSortingRepository<Consulta, String> {
	
	public Collection<Consulta> findByEmisor(String emisor);
	
	public Collection<Consulta> findByReceptor(String receptor);
}
