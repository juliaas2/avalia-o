package br.insper.avaliacao.projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    private final String USER_API = "http://184.72.80.215/usuario";
    private final String VALIDATE_API = "http://184.72.80.215/usuario/validate";

    public Artigo createArtigo(Artigo artigo, String token) {
        if (isAdmin(token)) {
            return artigoRepository.save(artigo);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
    }

    public void deleteArtigo(String id, String token) {
        if (isAdmin(token)) {
            artigoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }
    }

    public List<Artigo> listArtigos(String token) {
        if (isAuthorized(token)) {
            return artigoRepository.findAll();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
    }

    public Optional<Artigo> findArtigoById(String id, String token) {
        if (isAuthorized(token)) {
            return artigoRepository.findById(id);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
    }

    private boolean isAuthorized(String token) {
        String role = getRoleFromToken(token);
        return "ADMIN".equals(role) || "DEVELOPER".equals(role);
    }

    private boolean isAdmin(String token) {
        return "ADMIN".equals(getRoleFromToken(token));
    }

    private String getRoleFromToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            var response = restTemplate.getForObject(VALIDATE_API, Map.class);
            return (String) response.get("papel");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
    }
}