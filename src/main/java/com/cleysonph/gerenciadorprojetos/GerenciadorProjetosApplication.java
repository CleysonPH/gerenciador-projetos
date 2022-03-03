package com.cleysonph.gerenciadorprojetos;

import java.time.LocalDate;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class GerenciadorProjetosApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorProjetosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var admin = Usuario.builder()
            .nome("Admin")
            .nascimento(LocalDate.of(1996, 1, 1))
            .status(Usuario.Status.ATIVO)
            .email("admin@mail.com")
            .password(passwordEncoder.encode("senha@123"))
            .role(Usuario.Role.ADMIN)
            .build();
        usuarioRepository.save(admin);
    }

}
