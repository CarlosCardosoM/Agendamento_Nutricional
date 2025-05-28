package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }