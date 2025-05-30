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

    @GetMapping("/cliente/{clienteMatricula}")
    public ResponseEntity<List<Consulta>> listarConsultasPorCliente(@PathVariable Long clienteMatricula) {
        List<Consulta> consultas = consultaService.listarConsultasPorCliente(clienteMatricula);
        return ResponseEntity.ok(consultas);
    }

    @DeleteMapping("/{consultaId}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long consultaId) {
        consultaService.cancelarConsulta(consultaId);
        return ResponseEntity.noContent().build();
    }
}
