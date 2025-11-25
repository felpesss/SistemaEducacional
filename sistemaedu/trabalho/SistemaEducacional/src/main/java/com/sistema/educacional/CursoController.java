package com.sistema.educacional;

import com.sistema.educacional.Curso;
import com.sistema.educacional.CursoRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        curso.setCriadoEm(LocalDateTime.now());
        Curso saved = cursoRepository.save(curso);
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", saved.getNomeCurso());
        auditoriaService.registrarLog("curso", "CREATE", String.valueOf(saved.getIdCurso()), curso.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso cursoDetalhes) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNomeCurso(cursoDetalhes.getNomeCurso());
                    curso.setDescricao(cursoDetalhes.getDescricao());
                    curso.setCargaHoraria(cursoDetalhes.getCargaHoraria());
                    curso.setDuracaoSemestres(cursoDetalhes.getDuracaoSemestres());
                    curso.setAtivo(cursoDetalhes.getAtivo());
                    Curso updated = cursoRepository.save(curso);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", updated.getNomeCurso());
                    auditoriaService.registrarLog("curso", "UPDATE", String.valueOf(updated.getIdCurso()), cursoDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    cursoRepository.delete(curso);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nome", curso.getNomeCurso());
                    auditoriaService.registrarLog("curso", "DELETE", String.valueOf(curso.getIdCurso()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
