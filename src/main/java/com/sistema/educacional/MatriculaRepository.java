package com.sistema.educacional;

import com.sistema.educacional.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByRaAluno(String raAluno);
    List<Matricula> findByIdTurma(Long idTurma);
}
