package com.sistema.educacional.repository;

import com.sistema.educacional.model.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, String> {
}
