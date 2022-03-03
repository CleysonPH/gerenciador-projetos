package com.cleysonph.gerenciadorprojetos.api.v1.projeto.services;

import java.util.List;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters.ProjetoFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjetoService {

    ProjetoResponse create(ProjetoRequest projetoRequest);

    Page<ProjetoResponse> findAll(ProjetoFindAllQueryFilter queryFilter, Pageable pageable);

    ProjetoResponse findById(Long projetoId);

    void deleteById(Long projetoId);

    ProjetoResponse updateById(ProjetoRequest projetoRequest, Long projetoId);

    DefaultResponse markAsAceito(Long projetoId);

    List<TarefaResponse> findTarefasByProjetoId(Long projetoId);

}
