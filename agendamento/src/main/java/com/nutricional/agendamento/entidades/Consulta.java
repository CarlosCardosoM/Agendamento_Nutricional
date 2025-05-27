package com.nutricional.agendamento.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_matricula") // Referencia a matrícula (PK do Cliente)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id") // Referencia o ID do Funcionario
    private Funcionario funcionario;

    @OneToOne
    private HorarioDisponivel horario;

    private String observacoes;

    // Getters e Setters

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public HorarioDisponivel getHorario() {
        return horario;
    }

    public void setHorario(HorarioDisponivel horario) {
        this.horario = horario;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
