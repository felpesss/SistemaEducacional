package com.sistema.educacional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turma")
public class Turma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long idTurma;
    
    @Column(name = "nome_turma", nullable = false)
    private String nomeTurma;
    
    @Column(name = "id_disciplina")
    private Long idDisciplina;
    
    @Column(name = "id_professor")
    private Long idProfessor;
    
    @Column(name = "semestre")
    private Integer semestre;
    
    @Column(name = "ano")
    private Integer ano;
    
    @Column(name = "horario")
    private String horario;
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "criado_por")
    private String criadoPor;

    public Long getIdTurma() { return idTurma; }
    public void setIdTurma(Long idTurma) { this.idTurma = idTurma; }
    
    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }
    
    public Long getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(Long idDisciplina) { this.idDisciplina = idDisciplina; }
    
    public Long getIdProfessor() { return idProfessor; }
    public void setIdProfessor(Long idProfessor) { this.idProfessor = idProfessor; }
    
    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }
    
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    
    public String getCriadoPor() { return criadoPor; }
    public void setCriadoPor(String criadoPor) { this.criadoPor = criadoPor; }
}
