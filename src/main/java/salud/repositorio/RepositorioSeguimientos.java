package salud.repositorio;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Seguimiento;

public interface RepositorioSeguimientos extends PagingAndSortingRepository<Seguimiento, String> {
	
	public Collection<Seguimiento> findByIdGrupo(String idGrupo);
}
