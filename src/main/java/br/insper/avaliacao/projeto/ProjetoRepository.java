package br.insper.avaliacao.projeto;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjetoRepository extends MongoRepository<Projeto, String> {
    List<Projeto> findByStatus(String status);
}
