package com.nutricional.agendamento.controle;

import com.nutricional.agendamento.entidades.Funcionario;
import com.nutricional.agendamento.entidades.HorarioDisponivel;
import com.nutricional.agendamento.service.HorarioDisponivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioDisponivelController {

    @Autowired
    private HorarioDisponivelService horarioService;

    @PostMapping
    public ResponseEntity<HorarioDisponivel> cadastrar(@RequestBody HorarioDisponivel horario) {
        return ResponseEntity.ok(horarioService.cadastrarHorarioDisponivel(horario));
    }

    @GetMapping
    public ResponseEntity<List<HorarioDisponivel>> listar() {
        return ResponseEntity.ok(horarioService.listarHorariosDisponiveis());
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Long id,
                                        @AuthenticationPrincipal Funcionario funcionario) {
        horarioService.removerHorarioDisponivel(id, funcionario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDisponivel> editar(@PathVariable Long id,
                                                    @RequestBody HorarioDisponivel horario,
                                                    @AuthenticationPrincipal Funcionario funcionario) {
        return ResponseEntity.ok(horarioService.editarHorarioDisponivel(id, horario, funcionario));
    }
}
