package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByMatricula(Long matricula);
}