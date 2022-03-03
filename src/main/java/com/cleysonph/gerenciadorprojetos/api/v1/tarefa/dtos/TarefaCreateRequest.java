package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.validators.ProjetoExistsById;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Positive
    @ProjetoExistsById
    private Long projetoId;

}
