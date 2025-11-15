package com.sistema.educacional;

import com.sistema.educacional.Disciplina;
import com.sistema.educacional.DisciplinaRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{idCurso}")
    public List<Disciplina> buscarPorCurso(@PathVariable Long idCurso) {
        return disciplinaRepository.findByIdCurso(idCurso);
    }

    @PostMapping
    public Disciplina criar(@RequestBody Disciplina disciplina) {
        disciplina.setCriadoEm(LocalDateTime.now());
        Disciplina saved = disciplinaRepository.save(disciplina);
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", saved.getNomeDisciplina());
        auditoriaService.registrarLog("disciplina", "CREATE", String.valueOf(saved.getIdDisciplina()), disciplina.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @RequestBody Disciplina disciplinaDetalhes) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> {
                    disciplina.setNomeDisciplina(disciplinaDetalhes.getNomeDisciplina());
                    disciplina.setCargaHoraria(disciplinaDetalhes.getCargaHoraria());
                    disciplina.setIdCurso(disciplinaDetalhes.getIdCurso());
                    disciplina.setEmenta(disciplinaDetalhes.getEmenta());
                    disciplina.setAtivo(disciplinaDetalhes.getAtivo());
                    Disciplina updated = disciplinaRepository.save(disciplina);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", updated.getNomeDisciplina());
                    auditoriaService.registrarLog("disciplina", "UPDATE", String.valueOf(updated.getIdDisciplina()), disciplinaDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> {
                    disciplinaRepository.delete(disciplina);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", disciplina.getNomeDisciplina());
                    auditoriaService.registrarLog("disciplina", "DELETE", String.valueOf(disciplina.getIdDisciplina()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
