package com.sistema.educacional;

import com.sistema.educacional.Nota;
import com.sistema.educacional.NotaRepository;
import com.sistema.educacional.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarPorId(@PathVariable Long id) {
        return notaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{idMatricula}")
    public List<Nota> buscarPorMatricula(@PathVariable Long idMatricula) {
        return notaRepository.findByIdMatricula(idMatricula);
    }

    @PostMapping
    public Nota criar(@RequestBody Nota nota) {
        nota.setCriadoEm(LocalDateTime.now());
        Nota saved = notaRepository.save(nota);
        Map<String, Object> dados = new HashMap<>();
        dados.put("tipo", saved.getTipoAvaliacao());
        dados.put("nota", saved.getNota());
        auditoriaService.registrarLog("nota", "CREATE", String.valueOf(saved.getIdNota()), nota.getCriadoPor(), dados);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizar(@PathVariable Long id, @RequestBody Nota notaDetalhes) {
        return notaRepository.findById(id)
                .map(nota -> {
                    nota.setIdMatricula(notaDetalhes.getIdMatricula());
                    nota.setTipoAvaliacao(notaDetalhes.getTipoAvaliacao());
                    nota.setNota(notaDetalhes.getNota());
                    nota.setDataAvaliacao(notaDetalhes.getDataAvaliacao());
                    nota.setPeso(notaDetalhes.getPeso());
                    nota.setObservacao(notaDetalhes.getObservacao());
                    Nota updated = notaRepository.save(nota);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("nota", updated.getNota());
                    auditoriaService.registrarLog("nota", "UPDATE", String.valueOf(updated.getIdNota()), notaDetalhes.getCriadoPor(), dados);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return notaRepository.findById(id)
                .map(nota -> {
                    notaRepository.delete(nota);
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("id", nota.getIdNota());
                    auditoriaService.registrarLog("nota", "DELETE", String.valueOf(nota.getIdNota()), "system", dados);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
