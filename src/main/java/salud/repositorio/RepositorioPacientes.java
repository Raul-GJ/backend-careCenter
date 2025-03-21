package salud.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Paciente;

@NoRepositoryBean
public interface RepositorioPacientes extends PagingAndSortingRepository<Paciente, String> {

}
