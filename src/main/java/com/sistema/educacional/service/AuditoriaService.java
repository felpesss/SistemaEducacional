package com.sistema.educacional.service;

import com.sistema.educacional.model.LogAuditoria;
import com.sistema.educacional.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.Map;

// salva logs no mongo (opcional)
@Service
@ConditionalOnProperty(name = "mongodb.enabled", havingValue = "true", matchIfMissing = false)
public class AuditoriaService {
    
    @Autowired(required = false)
    private LogAuditoriaRepository logRepository;
    
    public void registrarLog(String tabela, String operacao, String idRegistro, 
                            String usuario, Map<String, Object> dadosNovos) {
        if (logRepository != null) {
            try {
                LogAuditoria log = new LogAuditoria();
                log.setTabela(tabela);
                log.setOperacao(operacao);
                log.setIdRegistro(idRegistro);
                log.setUsuario(usuario);
                log.setDadosNovos(dadosNovos);
                logRepository.save(log);
            } catch (Exception e) {
                System.out.println("Mongo indisponível - log ignorado");
            }
        }
    }
}
