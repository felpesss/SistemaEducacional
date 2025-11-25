package com.sistema.educacional;

import com.sistema.educacional.Professor;
import com.sistema.educacional.ProfessorRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        professor.setCriadoEm(LocalDateTime.now());
        Professor saved = professorRepository.save(professor);
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", saved.getNome());
        auditoriaService.registrarLog("professor", "CREATE", String.valueOf(saved.getIdProfessor()), professor.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professorDetalhes) {
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNome(professorDetalhes.getNome());
                    professor.setCpf(professorDetalhes.getCpf());
                    professor.setEmail(professorDetalhes.getEmail());
                    professor.setTelefone(professorDetalhes.getTelefone());
                    professor.setEspecialidade(professorDetalhes.getEspecialidade());
                    professor.setAtivo(professorDetalhes.getAtivo());
                    Professor updated = professorRepository.save(professor);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", updated.getNome());
                    auditoriaService.registrarLog("professor", "UPDATE", String.valueOf(updated.getIdProfessor()), professorDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(professor -> {
                    professorRepository.delete(professor);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", professor.getNome());
                    auditoriaService.registrarLog("professor", "DELETE", String.valueOf(professor.getIdProfessor()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
