package salud.repositorio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import salud.modelo.Paciente;
import salud.modelo.Sanitario;
import salud.modelo.TipoUsuario;
import salud.modelo.Usuario;

@NoRepositoryBean
public interface RepositorioUsuarios extends PagingAndSortingRepository<Usuario, String> {
	
	public Collection<Usuario> findByTipo(TipoUsuario tipo);
	
	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByDni(String dni);
	
	public Optional<Paciente> findByNss(String nss);
	
	public Optional<Sanitario> findBynCol(String nCol);
}
