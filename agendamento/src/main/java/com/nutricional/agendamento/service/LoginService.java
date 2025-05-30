package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.Funcionario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Funcionario funcionarioLogado;

    public void login(Funcionario funcionario) {
        this.funcionarioLogado = funcionario;
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void logout() {
        funcionarioLogado = null;
    }
}
