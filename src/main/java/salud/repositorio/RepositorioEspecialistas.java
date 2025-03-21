package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Especialista;

@NoRepositoryBean
public interface RepositorioEspecialistas extends PagingAndSortingRepository<Especialista, String> {

}
