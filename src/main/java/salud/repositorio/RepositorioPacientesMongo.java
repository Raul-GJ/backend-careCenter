package salud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import salud.modelo.Paciente;

public interface RepositorioPacientesMongo extends RepositorioPacientes, MongoRepository<Paciente, String> {

}
