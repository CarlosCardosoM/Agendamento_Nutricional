package com.nutricional.agendamento.controle;

import com.nutricional.agendamento.entidades.Consulta;
import com.nutricional.agendamento.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendarConsulta(
            @RequestParam Long clienteMatricula,
            @RequestParam Long funcionarioId,
            @RequestParam Long horarioId,
            @RequestParam(required = false) String observacoes) {
        Consulta consulta = consultaService.agendarConsulta(clienteMatricula, funcionarioId, horarioId, observacoes);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarConsulta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> editar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        return ResponseEntity.ok(consultaService.editarConsulta(id, consultaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteMatricula}")
    public ResponseEntity<List<Consulta>> listarConsultasPorCliente(@PathVariable Long clienteMatricula) {
        List<Consulta> consultas = consultaService.listarConsultasPorCliente(clienteMatricula);
        return ResponseEntity.ok(consultas);
    }
}
