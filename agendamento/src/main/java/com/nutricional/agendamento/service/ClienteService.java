package com.nutricional.agendamento.service;

import com.nutricional.agendamento.entidades.Cliente;
import com.nutricional.agendamento.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long matricula) {
        return clienteRepository.findById(matricula);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long matricula) {
        clienteRepository.deleteById(matricula);
    }
}