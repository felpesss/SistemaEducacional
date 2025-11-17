package com.sistema.educacional;

import com.sistema.educacional.Matricula;
import com.sistema.educacional.MatriculaRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        return matriculaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/aluno/{raAluno}")
    public List<Matricula> buscarPorAluno(@PathVariable String raAluno) {
        return matriculaRepository.findByRaAluno(raAluno);
    }

    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) {
        matricula.setCriadoEm(LocalDateTime.now());
        Matricula saved = matriculaRepository.save(matricula);
        Map<String, Object> dados = new HashMap<>();
        dados.put("ra", saved.getRaAluno());
        auditoriaService.registrarLog("matricula", "CREATE", String.valueOf(saved.getIdMatricula()), matricula.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable Long id, @RequestBody Matricula matriculaDetalhes) {
        return matriculaRepository.findById(id)
                .map(matricula -> {
                    matricula.setRaAluno(matriculaDetalhes.getRaAluno());
                    matricula.setIdTurma(matriculaDetalhes.getIdTurma());
                    matricula.setDataMatricula(matriculaDetalhes.getDataMatricula());
                    matricula.setStatus(matriculaDetalhes.getStatus());
                    Matricula updated = matriculaRepository.save(matricula);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("status", updated.getStatus());
                    auditoriaService.registrarLog("matricula", "UPDATE", String.valueOf(updated.getIdMatricula()), matriculaDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return matriculaRepository.findById(id)
                .map(matricula -> {
                    matriculaRepository.delete(matricula);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("id", matricula.getIdMatricula());
                    auditoriaService.registrarLog("matricula", "DELETE", String.valueOf(matricula.getIdMatricula()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
