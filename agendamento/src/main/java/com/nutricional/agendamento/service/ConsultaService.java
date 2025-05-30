package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.*;
import com.nutricional.agendamento.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private HorarioDisponivelRepository horarioDisponivelRepository;

    public Consulta agendarConsulta(Long clienteMatricula, Long funcionarioId, Long horarioId, String observacoes) {
        Cliente cliente = clienteRepository.findById(clienteMatricula)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        HorarioDisponivel horario = horarioDisponivelRepository.findById(horarioId)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        // Verifica se o horário já está agendado
        boolean jaAgendado = consultaRepository.findAll().stream()
                .anyMatch(c -> c.getHorario().getId().equals(horarioId));

        if (jaAgendado) {
            throw new RuntimeException("Horário já foi agendado!");
        }

        Consulta consulta = new Consulta();
        consulta.setCliente(cliente);
        consulta.setFuncionario(funcionario);
        consulta.setHorario(horario);
        consulta.setObservacoes(observacoes);

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultasPorCliente(Long clienteMatricula) {
        return consultaRepository.findByClienteMatricula(clienteMatricula);
    }

    public void cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consultaRepository.delete(consulta);
    }
}
