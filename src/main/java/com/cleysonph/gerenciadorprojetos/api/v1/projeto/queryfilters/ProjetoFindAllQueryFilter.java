package com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters;

import java.time.LocalDate;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;
import com.cleysonph.gerenciadorprojetos.core.queryfilters.ProjetoQueryFilter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoFindAllQueryFilter implements ProjetoQueryFilter {

    private String nome;

    private String descricao;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataInicioGte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataInicioLte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataFimGte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataFimLte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataEntregaGte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataEntregaLte;

    private String responsavel;

    private String lider;

    private List<Status> status;

}
