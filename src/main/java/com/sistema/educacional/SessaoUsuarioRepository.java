package com.sistema.educacional;

import com.sistema.educacional.SessaoUsuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoUsuarioRepository extends MongoRepository<SessaoUsuario, String> {
    
    // Buscar sessão ativa por usuário
    Optional<SessaoUsuario> findByUsuarioIdAndAtivaTrue(Long usuarioId);
    
    // Buscar sessão por token
    Optional<SessaoUsuario> findByToken(String token);
    
    // Listar todas as sessões ativas
    List<SessaoUsuario> findByAtivaTrue();
    
    // Buscar sessões por usuário
    List<SessaoUsuario> findByUsuarioId(Long usuarioId);
    
    // Buscar sessões por período
    List<SessaoUsuario> findByDataLoginBetween(LocalDateTime inicio, LocalDateTime fim);
    
    // Contar usuários online
    @Query(value = "{ 'ativa': true }", count = true)
    Long contarUsuariosOnline();
}
