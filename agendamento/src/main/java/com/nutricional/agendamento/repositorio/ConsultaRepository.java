package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByClienteMatricula(String matricula);
    List<Consulta> findByFuncionarioId(Long funcionarioId);
}
