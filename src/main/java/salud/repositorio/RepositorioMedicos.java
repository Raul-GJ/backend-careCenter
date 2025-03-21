package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.MedicoFamilia;

@NoRepositoryBean
public interface RepositorioMedicos extends PagingAndSortingRepository<MedicoFamilia, String> {

}
