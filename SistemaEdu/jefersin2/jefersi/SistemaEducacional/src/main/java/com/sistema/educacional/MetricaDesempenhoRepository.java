package com.sistema.educacional;

import com.sistema.educacional.MetricaDesempenho;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MetricaDesempenhoRepository extends MongoRepository<MetricaDesempenho, String> {
    
    List<MetricaDesempenho> findByAlunoId(Long alunoId);
    
    List<MetricaDesempenho> findByDisciplinaId(Long disciplinaId);
    
    List<MetricaDesempenho> findByAlunoIdAndDisciplinaId(Long alunoId, Long disciplinaId);
    
    List<MetricaDesempenho> findByDataCalculoBetween(LocalDate inicio, LocalDate fim);
}
