package salud.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.NotaPaciente;

public interface RepositorioNotas extends PagingAndSortingRepository<NotaPaciente, String> {

}
