package com.sistema.educacional.controller;

import com.sistema.educacional.model.Aluno;
import com.sistema.educacional.repository.AlunoRepository;
import com.sistema.educacional.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private AuditoriaService auditoriaService;
    
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    @GetMapping("/{ra}")
    public ResponseEntity<Aluno> buscarPorRa(@PathVariable String ra) {
        return alunoRepository.findById(ra)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno salvo = alunoRepository.save(aluno);
        
        if (auditoriaService != null) {
            Map<String, Object> dados = new HashMap<>();
            dados.put("ra", salvo.getRa());
            dados.put("nome", salvo.getNome());
            auditoriaService.registrarLog("Aluno", "INSERT", salvo.getRa(), "admin", dados);
        }
        
        return ResponseEntity.ok(salvo);
    }
    
    @PutMapping("/{ra}")
    public ResponseEntity<Aluno> atualizar(@PathVariable String ra, @RequestBody Aluno aluno) {
        return alunoRepository.findById(ra)
            .map(existente -> {
                aluno.setRa(ra);
                Aluno atualizado = alunoRepository.save(aluno);
                
                if (auditoriaService != null) {
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("ra", atualizado.getRa());
                    dados.put("nome", atualizado.getNome());
                    auditoriaService.registrarLog("Aluno", "UPDATE", ra, "admin", dados);
                }
                
                return ResponseEntity.ok(atualizado);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{ra}")
    public ResponseEntity<?> deletar(@PathVariable String ra) {
        return alunoRepository.findById(ra)
            .map(aluno -> {
                alunoRepository.delete(aluno);
                
                if (auditoriaService != null) {
                    Map<String, Object> dados = new HashMap<>();
                    dados.put("ra", ra);
                    auditoriaService.registrarLog("Aluno", "DELETE", ra, "admin", dados);
                }
                
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
