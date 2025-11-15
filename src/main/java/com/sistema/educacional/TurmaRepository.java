package com.sistema.educacional;

import com.sistema.educacional.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByIdDisciplina(Long idDisciplina);
    List<Turma> findByIdProfessor(Long idProfessor);
}
