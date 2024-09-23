package br.insper.avaliacao.projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
@RestController
@RequestMapping("/projects")
public class ProjetoController {
    @Autowired
    private ProjetoService projectService;

    @PostMapping
    public Projeto createProject(@RequestBody Projeto project) throws Exception {
        return projectService.createProject(project);
    }

    @GetMapping
    public List<Projeto> listProjects(@RequestParam Optional<String> status) {
        return projectService.listProjects(status);
    }

    @PostMapping("/{projectId}/members")
    public Projeto addMember(@PathVariable String projectId, @RequestBody String cpf) throws Exception {
        return projectService.addMember(projectId, cpf);
    }
}

