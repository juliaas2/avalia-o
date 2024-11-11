package br.insper.avaliacao.projeto;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtigoRepository extends MongoRepository<Artigo, String> {
    List<Artigo> findAll();

    Optional<Artigo> findById(String id);

    // criar artigo
    Artigo save(Artigo artigo);

    // deletar artigo
    void deleteById(String id);


}
