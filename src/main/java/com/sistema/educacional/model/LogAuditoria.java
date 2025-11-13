package com.sistema.educacional.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Map;

// log no mongo
@Document(collection = "logs_auditoria")
public class LogAuditoria {
    
    @Id
    private String id;
    private String tabela;
    private String operacao;
    private String idRegistro;
    private String usuario;
    private LocalDateTime dataOperacao;
    private Map<String, Object> dadosAnteriores;
    private Map<String, Object> dadosNovos;
    
    public LogAuditoria() {
        this.dataOperacao = LocalDateTime.now();
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTabela() { return tabela; }
    public void setTabela(String tabela) { this.tabela = tabela; }
    
    public String getOperacao() { return operacao; }
    public void setOperacao(String operacao) { this.operacao = operacao; }
    
    public String getIdRegistro() { return idRegistro; }
    public void setIdRegistro(String idRegistro) { this.idRegistro = idRegistro; }
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public LocalDateTime getDataOperacao() { return dataOperacao; }
    public void setDataOperacao(LocalDateTime dataOperacao) { this.dataOperacao = dataOperacao; }
    
    public Map<String, Object> getDadosAnteriores() { return dadosAnteriores; }
    public void setDadosAnteriores(Map<String, Object> dadosAnteriores) { this.dadosAnteriores = dadosAnteriores; }
    
    public Map<String, Object> getDadosNovos() { return dadosNovos; }
    public void setDadosNovos(Map<String, Object> dadosNovos) { this.dadosNovos = dadosNovos; }
}
