package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByClienteMatricula(Long clienteMatricula);
    List<Consulta> findByFuncionarioId(Long funcionarioId);
}
