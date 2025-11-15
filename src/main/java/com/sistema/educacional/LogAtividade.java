package com.sistema.educacional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs_atividade")
public class LogAtividade {
    @Id
    private String id;
    private Long usuarioId;
    private String tipoAcao;
    private String detalhes;
    private LocalDateTime dataHora;
    private String ip;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getTipoAcao() { return tipoAcao; }
    public void setTipoAcao(String tipoAcao) { this.tipoAcao = tipoAcao; }
    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
}
