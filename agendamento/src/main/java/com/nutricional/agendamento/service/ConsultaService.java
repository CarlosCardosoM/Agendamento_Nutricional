package com.nutricional.agendamento.service;

<<<<<<< HEAD
import com.nutricional.agendamento.entidades.*;
import com.nutricional.agendamento.repositorio.*;
=======
import com.nutricional.agendamento.entidades.Consulta;
import com.nutricional.agendamento.repositorio.ConsultaRepository;
>>>>>>> 3e8cdc1a991f4f6485be0b95f0ba5c419ee3e65f
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

<<<<<<< HEAD
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
=======
    public Consulta agendarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarConsulta(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public Consulta editarConsulta(Long id, Consulta consultaAtualizada) {
        Consulta consulta = buscarConsulta(id);

        consulta.setCliente(consultaAtualizada.getCliente());
        consulta.setFuncionario(consultaAtualizada.getFuncionario());
        consulta.setHorario(consultaAtualizada.getHorario());
        consulta.setObservacoes(consultaAtualizada.getObservacoes());
>>>>>>> 3e8cdc1a991f4f6485be0b95f0ba5c419ee3e65f

        return consultaRepository.save(consulta);
    }

<<<<<<< HEAD
    public List<Consulta> listarConsultasPorCliente(Long clienteMatricula) {
        return consultaRepository.findByClienteMatricula(clienteMatricula);
    }

    public void cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consultaRepository.delete(consulta);
=======
    public void cancelarConsulta(Long id) {
        consultaRepository.deleteById(id);
>>>>>>> 3e8cdc1a991f4f6485be0b95f0ba5c419ee3e65f
    }
}
