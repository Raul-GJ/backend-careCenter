package salud.repositorio;

import java.util.Collection;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.AsignacionEstudio;

@NoRepositoryBean
public interface RepositorioAsignacionEstudio extends PagingAndSortingRepository<AsignacionEstudio, String> {
	
	public Collection<AsignacionEstudio> findByEspecialista_Id(String especialistaId);
	
	public Collection<AsignacionEstudio> findByEstudio_Id(String estudioId);

}
