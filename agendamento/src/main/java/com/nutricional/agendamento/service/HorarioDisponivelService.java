package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.Funcionario;
import com.nutricional.agendamento.entidades.HorarioDisponivel;
import com.nutricional.agendamento.repositorio.HorarioDisponivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioDisponivelService {

    @Autowired
    private HorarioDisponivelRepository horarioDisponivelRepository;

    @Autowired
    private LoginService loginService;

    public HorarioDisponivel cadastrarHorarioDisponivel(HorarioDisponivel horario) {
        Funcionario funcionario = loginService.getFuncionarioLogado();

        if (funcionario == null) {
            throw new RuntimeException("Funcionário não está logado");
        }

        horario.setFuncionario(funcionario);

        if (verificarConflitoDeHorario(horario)) {
            throw new RuntimeException("Conflito de horário detectado!");
        }

        return horarioDisponivelRepository.save(horario);
    }

    public List<HorarioDisponivel> listarHorariosDisponiveis() {
        Funcionario funcionario = loginService.getFuncionarioLogado();

        if (funcionario == null) {
            throw new RuntimeException("Funcionário não está logado");
        }

        return horarioDisponivelRepository.findByFuncionarioId(funcionario.getId());
    }


    public List<HorarioDisponivel> listarHorariosDisponiveis(Funcionario funcionario) {
        return horarioDisponivelRepository.findByFuncionarioId(funcionario.getId());
    }

    public void removerHorarioDisponivel(Long id, Funcionario funcionario) {
        HorarioDisponivel horario = horarioDisponivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        if (!horario.getFuncionario().getId().equals(funcionario.getId())) {
            throw new RuntimeException("Acesso negado: horário não pertence ao funcionário.");
        }

        horarioDisponivelRepository.deleteById(id);
    }

    public HorarioDisponivel editarHorarioDisponivel(Long id, HorarioDisponivel novoHorario, Funcionario funcionario) {
        HorarioDisponivel horario = horarioDisponivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        if (!horario.getFuncionario().getId().equals(funcionario.getId())) {
            throw new RuntimeException("Acesso negado: horário não pertence ao funcionário.");
        }

        horario.setData(novoHorario.getData());
        horario.setHoraInicio(novoHorario.getHoraInicio());
        horario.setHoraFim(novoHorario.getHoraFim());

        if (verificarConflitoDeHorario(horario)) {
            throw new RuntimeException("Conflito de horário ao editar!");
        }

        return horarioDisponivelRepository.save(horario);
    }

    private boolean verificarConflitoDeHorario(HorarioDisponivel novoHorario) {
        List<HorarioDisponivel> horarios =
                horarioDisponivelRepository.findByFuncionarioId(novoHorario.getFuncionario().getId());

        for (HorarioDisponivel h : horarios) {
            boolean mesmoDia = h.getData().equals(novoHorario.getData());
            boolean sobreposto = !(novoHorario.getHoraFim().isBefore(h.getHoraInicio()) ||
                    novoHorario.getHoraInicio().isAfter(h.getHoraFim()));
            if (mesmoDia && sobreposto && !h.getId().equals(novoHorario.getId())) {
                return true;
            }
        }
        return false;
    }
}
