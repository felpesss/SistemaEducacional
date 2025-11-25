package com.sistema.educacional;

import com.sistema.educacional.LogAuditoria;
import com.sistema.educacional.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

@Service
public class AuditoriaService {
    
    @Autowired
    private LogAuditoriaRepository logRepository;
    
    public void registrarLog(String tabela, String operacao, String idRegistro, 
                            String usuario, Map<String, Object> dadosNovos) {
        try {
            LogAuditoria log = new LogAuditoria();
            log.setId("AUD" + UUID.randomUUID().toString().replace("-", "").substring(0, 20));
            log.setTabela(tabela);
            log.setOperacao(operacao);
            log.setIdRegistro(idRegistro);
            log.setUsuario(usuario);
            log.setDadosNovos(dadosNovos.toString());
            logRepository.save(log);
        } catch (Exception e) {
            System.out.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}
