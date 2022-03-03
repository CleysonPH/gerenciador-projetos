package com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos;

import java.time.LocalDate;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;
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
public class ProjetoResponse extends ResourceResponse {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataEntrega;
    private String responsavelNome;
    private String liderNome;
    private Status status;

    @JsonIgnore
    public boolean isCriado() {
        return status.equals(Status.CRIADO);
    }

}
