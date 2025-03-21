package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Usuario;

@NoRepositoryBean
public interface RepositorioUsuarios extends PagingAndSortingRepository<Usuario, String>{
	
}
