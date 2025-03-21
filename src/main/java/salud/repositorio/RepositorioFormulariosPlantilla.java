package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.PlantillaFormulario;

@NoRepositoryBean
public interface RepositorioFormulariosPlantilla extends PagingAndSortingRepository<PlantillaFormulario, String> {

}
