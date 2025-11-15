package com.sistema.educacional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "metricas_desempenho")
public class MetricaDesempenho {
    @Id
    private String id;
    private Long alunoId;
    private Long disciplinaId;
    private Double mediaNotas;
    private Integer totalAvaliacoes;
    private Double percentualPresenca;
    private LocalDate dataCalculo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long disciplinaId) { this.disciplinaId = disciplinaId; }
    public Double getMediaNotas() { return mediaNotas; }
    public void setMediaNotas(Double mediaNotas) { this.mediaNotas = mediaNotas; }
    public Integer getTotalAvaliacoes() { return totalAvaliacoes; }
    public void setTotalAvaliacoes(Integer totalAvaliacoes) { this.totalAvaliacoes = totalAvaliacoes; }
    public Double getPercentualPresenca() { return percentualPresenca; }
    public void setPercentualPresenca(Double percentualPresenca) { this.percentualPresenca = percentualPresenca; }
    public LocalDate getDataCalculo() { return dataCalculo; }
    public void setDataCalculo(LocalDate dataCalculo) { this.dataCalculo = dataCalculo; }
}
