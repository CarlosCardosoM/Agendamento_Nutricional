package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.Consulta;
import com.nutricional.agendamento.repositorio.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

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

        return consultaRepository.save(consulta);
    }

    public void cancelarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
