package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaUpdateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

}
