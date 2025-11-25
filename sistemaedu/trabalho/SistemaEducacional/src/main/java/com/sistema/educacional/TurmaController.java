package com.sistema.educacional;

import com.sistema.educacional.Turma;
import com.sistema.educacional.TurmaRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return turmaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disciplina/{idDisciplina}")
    public List<Turma> buscarPorDisciplina(@PathVariable Long idDisciplina) {
        return turmaRepository.findByIdDisciplina(idDisciplina);
    }

    @PostMapping
    public Turma criar(@RequestBody Turma turma) {
        turma.setCriadoEm(LocalDateTime.now());
        Turma saved = turmaRepository.save(turma);
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", saved.getNomeTurma());
        auditoriaService.registrarLog("turma", "CREATE", String.valueOf(saved.getIdTurma()), turma.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turmaDetalhes) {
        return turmaRepository.findById(id)
                .map(turma -> {
                    turma.setNomeTurma(turmaDetalhes.getNomeTurma());
                    turma.setIdDisciplina(turmaDetalhes.getIdDisciplina());
                    turma.setIdProfessor(turmaDetalhes.getIdProfessor());
                    turma.setSemestre(turmaDetalhes.getSemestre());
                    turma.setAno(turmaDetalhes.getAno());
                    turma.setHorario(turmaDetalhes.getHorario());
                    turma.setAtivo(turmaDetalhes.getAtivo());
                    Turma updated = turmaRepository.save(turma);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", updated.getNomeTurma());
                    auditoriaService.registrarLog("turma", "UPDATE", String.valueOf(updated.getIdTurma()), turmaDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return turmaRepository.findById(id)
                .map(turma -> {
                    turmaRepository.delete(turma);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", turma.getNomeTurma());
                    auditoriaService.registrarLog("turma", "DELETE", String.valueOf(turma.getIdTurma()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
