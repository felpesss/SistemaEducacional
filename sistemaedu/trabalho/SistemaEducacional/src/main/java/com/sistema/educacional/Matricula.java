package com.sistema.educacional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;
    
    @Column(name = "ra_aluno", nullable = false)
    private String raAluno;
    
    @Column(name = "id_turma", nullable = false)
    private Long idTurma;
    
    @Column(name = "data_matricula")
    private LocalDate dataMatricula;
    
    @Column(name = "status")
    private String status; // ATIVA, TRANCADA, CONCLUIDA, CANCELADA
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "criado_por")
    private String criadoPor;

    public Long getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Long idMatricula) { this.idMatricula = idMatricula; }
    
    public String getRaAluno() { return raAluno; }
    public void setRaAluno(String raAluno) { this.raAluno = raAluno; }
    
    public Long getIdTurma() { return idTurma; }
    public void setIdTurma(Long idTurma) { this.idTurma = idTurma; }
    
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    
    public String getCriadoPor() { return criadoPor; }
    public void setCriadoPor(String criadoPor) { this.criadoPor = criadoPor; }
}
