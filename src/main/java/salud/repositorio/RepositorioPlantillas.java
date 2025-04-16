package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Plantilla;

@NoRepositoryBean
public interface RepositorioPlantillas extends PagingAndSortingRepository<Plantilla, String> {

}
