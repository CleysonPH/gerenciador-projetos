package com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.IsUsuario;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.UsuarioExistsById;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @FutureOrPresent
    private LocalDate dataInicio;

    @Future
    @NotNull
    private LocalDate dataFim;

    @NotNull
    @Positive
    @IsUsuario
    @UsuarioExistsById
    private Long responsavel;

}
