package com.nutricional.agendamento.controle;

import com.nutricional.agendamento.entidades.Cliente;
import com.nutricional.agendamento.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long matricula) {
        return clienteService.findById(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long matricula, @RequestBody Cliente cliente) {
        return clienteService.findById(matricula)
                .map(existing -> {
                    cliente.setMatricula(matricula);
                    return ResponseEntity.ok(clienteService.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long matricula) {
        if (clienteService.findById(matricula).isPresent()) {
            clienteService.deleteById(matricula);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}