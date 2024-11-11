package br.insper.avaliacao.projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projectRepository;

    private final String USER_API = "http://184.72.80.215:8080/usuario/";

    public Projeto createProject(Projeto project) throws Exception {
        if (verifyUserExists(project.getManagerCpf())) {
            return projectRepository.save(project);
        } else {
            throw new Exception("Gerente não encontrado.");
        }
    }

    public List<Projeto> listProjects(Optional<String> status) {
        return status.map(projectRepository::findByStatus)
                .orElse(projectRepository.findAll());
    }

    public Projeto addMember(String projectId, String cpf) throws Exception {
        if (!verifyUserExists(cpf)) {
            throw new Exception("Pessoa não encontrada.");
        }
        Projeto project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Projeto não encontrado."));

        if (project.getStatus().equals("FINALIZADO")) {
            throw new Exception("Projeto finalizado não pode receber novos membros.");
        }

        project.getMembers().add(cpf);
        return projectRepository.save(project);
    }

    private boolean verifyUserExists(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(USER_API + cpf, String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
