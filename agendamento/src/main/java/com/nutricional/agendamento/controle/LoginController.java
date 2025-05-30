package com.nutricional.agendamento.controle;

import com.nutricional.agendamento.dto.LoginRequest;
import com.nutricional.agendamento.entidades.Funcionario;
import com.nutricional.agendamento.repositorio.FuncionarioRepository;
import com.nutricional.agendamento.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Funcionario funcionario = funcionarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (funcionario == null || !funcionario.getSenha().equals(request.getSenha())) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        loginService.login(funcionario);
        return ResponseEntity.ok("Login realizado com sucesso para: " + funcionario.getNome());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        loginService.logout();
        return ResponseEntity.ok("Logout realizado com sucesso");
    }
}
