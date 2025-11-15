package com.sistema.educacional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nota")
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Long idNota;
    
    @Column(name = "id_matricula", nullable = false)
    private Long idMatricula;
    
    @Column(name = "tipo_avaliacao")
    private String tipoAvaliacao; // PROVA, TRABALHO, SEMINARIO, PROJETO
    
    @Column(name = "nota", nullable = false)
    private Double nota;
    
    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;
    
    @Column(name = "peso")
    private Double peso;
    
    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "criado_por")
    private String criadoPor;

    public Long getIdNota() { return idNota; }
    public void setIdNota(Long idNota) { this.idNota = idNota; }
    
    public Long getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Long idMatricula) { this.idMatricula = idMatricula; }
    
    public String getTipoAvaliacao() { return tipoAvaliacao; }
    public void setTipoAvaliacao(String tipoAvaliacao) { this.tipoAvaliacao = tipoAvaliacao; }
    
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    
    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
    
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    
    public String getCriadoPor() { return criadoPor; }
    public void setCriadoPor(String criadoPor) { this.criadoPor = criadoPor; }
}
