package com.sistema.educacional;

import com.sistema.educacional.LogAtividade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAtividadeRepository extends MongoRepository<LogAtividade, String> {
    
    // Buscar logs por usuário
    List<LogAtividade> findByUsuarioId(Long usuarioId);
    
    // Buscar logs por tipo de ação
    List<LogAtividade> findByTipoAcao(String tipoAcao);
    
    // Buscar logs por período
    List<LogAtividade> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    
    // Buscar logs por usuário e período
    List<LogAtividade> findByUsuarioIdAndDataHoraBetween(
        Long usuarioId, 
        LocalDateTime inicio, 
        LocalDateTime fim
    );
    
    // Contar logs por usuário
    Long countByUsuarioId(Long usuarioId);
    
    // Logs de hoje
    @Query("{ 'dataHora': { $gte: ?0 } }")
    List<LogAtividade> findLogsDeHoje(LocalDateTime inicioDoDia);
}
