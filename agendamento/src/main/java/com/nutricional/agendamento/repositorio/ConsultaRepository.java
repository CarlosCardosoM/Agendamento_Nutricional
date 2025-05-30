package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByClienteMatricula(Long clienteMatricula);

    List<Consulta> findByFuncionarioId(Long funcionarioId);
=======

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
>>>>>>> 3e8cdc1a991f4f6485be0b95f0ba5c419ee3e65f
}

