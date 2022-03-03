package com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.UsuarioNonExistsByEmail;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.validators.Age;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsuarioRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @Past
    @NotNull
    @Age(min = 18, max = 100)
    private LocalDate nascimento;

    @Email
    @NotNull
    @NotEmpty
    @UsuarioNonExistsByEmail
    private String email;

    @NotNull
    private Role role;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String passwordConfirmation;

    @AssertTrue(message = "senhas não conferem")
    private boolean isPasswordConfirmation() {
        if (password != null && passwordConfirmation != null) {
            return password.equals(passwordConfirmation);
        }
        return true;
    }

}
