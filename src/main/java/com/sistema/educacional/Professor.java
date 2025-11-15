package com.sistema.educacional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "professor")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
    private Long idProfessor;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "especialidade")
    private String especialidade;
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "criado_por")
    private String criadoPor;

    public Long getIdProfessor() { return idProfessor; }
    public void setIdProfessor(Long idProfessor) { this.idProfessor = idProfessor; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    
    public String getCriadoPor() { return criadoPor; }
    public void setCriadoPor(String criadoPor) { this.criadoPor = criadoPor; }
}
