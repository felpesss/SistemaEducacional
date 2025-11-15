package com.sistema.educacional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "sessoes_usuario")
public class SessaoUsuario {
    @Id
    private String id;
    private Long usuarioId;
    private String token;
    private LocalDateTime dataLogin;
    private LocalDateTime dataLogout;
    private String ip;
    private boolean ativa;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public LocalDateTime getDataLogin() { return dataLogin; }
    public void setDataLogin(LocalDateTime dataLogin) { this.dataLogin = dataLogin; }
    public LocalDateTime getDataLogout() { return dataLogout; }
    public void setDataLogout(LocalDateTime dataLogout) { this.dataLogout = dataLogout; }
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}
