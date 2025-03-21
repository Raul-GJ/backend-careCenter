package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Consulta;

@NoRepositoryBean
public interface RepositorioConsultas extends PagingAndSortingRepository<Consulta, String> {

}
