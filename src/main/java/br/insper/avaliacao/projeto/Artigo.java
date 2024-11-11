package br.insper.avaliacao.projeto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Getter
@Setter
public class Projeto{
    @Id
    private String id;
    
}
