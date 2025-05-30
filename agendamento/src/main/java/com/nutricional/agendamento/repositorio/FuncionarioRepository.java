package com.nutricional.agendamento.repositorio;

import com.nutricional.agendamento.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByEmailAndSenha(String email, String senha);

}
