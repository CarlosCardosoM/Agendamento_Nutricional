package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.HorarioDisponivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {
    List<HorarioDisponivel> findByFuncionarioId(Long funcionarioId);
    List<HorarioDisponivel> findByData(LocalDate data);
    List<HorarioDisponivel> findByDataAndHora(LocalDate data, LocalTime hora);
    List<HorarioDisponivel> findByDataAndOcupado(LocalDate data, boolean ocupado);
    List<HorarioDisponivel> findByFuncionarioIdAndData(Long funcionarioId, LocalDate data);
}