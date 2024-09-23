package br.insper.avaliacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvaliacaoController {
    //criar rota hello word
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

}
