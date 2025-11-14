package com.sistema.educacional.model;

import javax.persistence.*;
import java.time.LocalDateTime;

// log de auditoria
@Entity
@Table(name = "auditoria")
public class LogAuditoria {
    
    @Id
    @Column(name = "id_auditoria")
    private String id;
    
    @Column(name = "tabela")
    private String tabela;
    
    @Column(name = "operacao")
    private String operacao;
    
    @Column(name = "id_registro")
    private String idRegistro;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "data_operacao")
    private LocalDateTime dataOperacao;
    
    @Column(name = "dados_anteriores", columnDefinition = "TEXT")
    private String dadosAnteriores;
    
    @Column(name = "dados_novos", columnDefinition = "TEXT")
    private String dadosNovos;
    
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
    
    public String getDadosAnteriores() { return dadosAnteriores; }
    public void setDadosAnteriores(String dadosAnteriores) { this.dadosAnteriores = dadosAnteriores; }
    
    public String getDadosNovos() { return dadosNovos; }
    public void setDadosNovos(String dadosNovos) { this.dadosNovos = dadosNovos; }
}
