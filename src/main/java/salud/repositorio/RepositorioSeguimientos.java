package salud.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Seguimiento;

public interface RepositorioSeguimientos extends PagingAndSortingRepository<Seguimiento, String> {

}
