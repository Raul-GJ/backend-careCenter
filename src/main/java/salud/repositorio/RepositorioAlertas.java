package salud.repositorio;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Alerta;

public interface RepositorioAlertas extends PagingAndSortingRepository<Alerta, String> {
	
	public Collection<Alerta> findByEmisor(String emisor);
	
	public Collection<Alerta> findByReceptor(String receptor);
}
