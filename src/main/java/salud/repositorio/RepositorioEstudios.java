package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Estudio;

@NoRepositoryBean
public interface RepositorioEstudios extends PagingAndSortingRepository<Estudio, String> {

}
