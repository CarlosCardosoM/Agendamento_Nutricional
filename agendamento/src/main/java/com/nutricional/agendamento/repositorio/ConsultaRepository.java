package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
