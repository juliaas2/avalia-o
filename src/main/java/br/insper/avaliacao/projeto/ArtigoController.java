package br.insper.avaliacao.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @PostMapping
    public ResponseEntity<Artigo> createArtigo(@RequestHeader("Authorization") String token, @RequestBody Artigo artigo) {
        return ResponseEntity.ok(artigoService.createArtigo(artigo, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtigo(@RequestHeader("Authorization") String token, @PathVariable String id) {
        artigoService.deleteArtigo(id, token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Artigo>> listArtigos(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(artigoService.listArtigos(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Artigo>> findArtigoById(@RequestHeader("Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(artigoService.findArtigoById(id, token));
    }

    // hello world
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
