package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Medico;

@NoRepositoryBean
public interface RepositorioMedicos extends PagingAndSortingRepository<Medico, String> {

}
