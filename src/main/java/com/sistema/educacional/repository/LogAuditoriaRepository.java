package com.sistema.educacional.repository;

import com.sistema.educacional.model.LogAuditoria;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// mongo opcional - desabilitado por padrao
@Repository
@ConditionalOnProperty(name = "mongodb.enabled", havingValue = "true")
public interface LogAuditoriaRepository extends MongoRepository<LogAuditoria, String> {
}
