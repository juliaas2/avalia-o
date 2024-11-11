package br.insper.avaliacao.projeto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Artigo {
    @Id
    private String id;
    private String titulo;
    private String resumo;
    private String conteudo;
}
