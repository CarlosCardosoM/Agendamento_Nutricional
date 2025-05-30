package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.HorarioDisponivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {
    List<HorarioDisponivel> findByFuncionarioId(Long funcionarioId);
}