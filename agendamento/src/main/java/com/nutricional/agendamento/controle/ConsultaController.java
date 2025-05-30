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

    // ✅ Método atual, usando parâmetros na URL (mantido)
    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendarConsulta(
            @RequestParam Long clienteMatricula,
            @RequestParam Long funcionarioId,
            @RequestParam Long horarioId,
            @RequestParam(required = false) String observacoes) {

        Consulta consulta = consultaService.agendarConsulta(clienteMatricula, funcionarioId, horarioId, observacoes);
        return ResponseEntity.ok(consulta);
    }

    // ✅ Novo método: POST /consultas com JSON no corpo da requisição
    @PostMapping
    public ResponseEntity<Consulta> agendarConsultaDireto(@RequestBody Consulta consulta) {
        // Aqui assumimos que Consulta tem cliente, funcionario e horario com IDs preenchidos
        Consulta consultaAgendada = consultaService.agendarConsulta(
                consulta.getCliente().getMatricula(),
                consulta.getFuncionario().getId(),
                consulta.getHorario().getId(),
                consulta.getObservacoes()
        );
        return ResponseEntity.ok(consultaAgendada);
    }

    // ✅ Listar todas as consultas
    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    // ✅ Buscar uma consulta específica
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarConsulta(id));
    }

    // ✅ Editar uma consulta
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> editar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        return ResponseEntity.ok(consultaService.editarConsulta(id, consultaAtualizada));
    }

    // ✅ Cancelar uma consulta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Listar consultas de um cliente específico
    @GetMapping("/cliente/{clienteMatricula}")
    public ResponseEntity<List<Consulta>> listarConsultasPorCliente(@PathVariable Long clienteMatricula) {
        List<Consulta> consultas = consultaService.listarConsultasPorCliente(clienteMatricula);
        return ResponseEntity.ok(consultas);
    }
}
