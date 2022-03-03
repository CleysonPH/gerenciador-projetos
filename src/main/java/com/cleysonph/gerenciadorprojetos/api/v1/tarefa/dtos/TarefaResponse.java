package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos;


import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TarefaResponse extends ResourceResponse {

    private Long id;
    private String nome;
    private Status status;
    private Long projetoId;

    @JsonIgnore
    public boolean isCriada() {
        return status.equals(Tarefa.Status.CRIADA);
    }

}
