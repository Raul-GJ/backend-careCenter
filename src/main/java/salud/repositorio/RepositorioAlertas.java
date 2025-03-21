package salud.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Alerta;

public interface RepositorioAlertas extends PagingAndSortingRepository<Alerta, String> {

}
