package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.Funcionario;
import com.nutricional.agendamento.repositorio.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario cadastrar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario atualizar(Long id, Funcionario novoFuncionario) {
        Funcionario funcionario = buscarPorId(id);
        funcionario.setNome(novoFuncionario.getNome());
        funcionario.setEspecialidade(novoFuncionario.getEspecialidade());
        funcionario.setEmail(novoFuncionario.getEmail());
        funcionario.setSenha(novoFuncionario.getSenha());
        return funcionarioRepository.save(funcionario);
    }

}
