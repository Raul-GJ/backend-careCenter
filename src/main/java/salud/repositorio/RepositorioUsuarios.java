package salud.repositorio;

import java.util.Collection;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;

@NoRepositoryBean
public interface RepositorioUsuarios extends PagingAndSortingRepository<Usuario, String> {
	
	public Collection<Usuario> findByTipo(TipoUsuario tipo);
}
