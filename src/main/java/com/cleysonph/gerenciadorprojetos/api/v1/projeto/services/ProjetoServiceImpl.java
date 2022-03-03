package com.cleysonph.gerenciadorprojetos.api.v1.projeto.services;

import static com.cleysonph.gerenciadorprojetos.core.specifications.ProjetoSpecifications.withLider;
import static com.cleysonph.gerenciadorprojetos.core.specifications.ProjetoSpecifications.withResponsavel;

import java.util.List;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.mappers.ProjetoMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters.ProjetoFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers.TarefaMapper;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;
import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final TarefaMapper tarefaMapper;
    private final ProjetoMapper projetoMapper;
    private final SecurityUtils securityUtils;
    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    @Override
    public ProjetoResponse create(ProjetoRequest projetoRequest) {
        var projetoToCreate = projetoMapper.toModel(projetoRequest);
        projetoToCreate.setLider(securityUtils.getAuthenticatedUser().getUsuario());
        var createdProjeto = projetoRepository.save(projetoToCreate);
        return projetoMapper.toResponse(createdProjeto);
    }

    @Override
    public Page<ProjetoResponse> findAll(ProjetoFindAllQueryFilter queryFilter, Pageable pageable) {
        var spec = getFindAllSpec(queryFilter);
        return projetoRepository.findAll(spec, pageable)
            .map(projetoMapper::toResponse);
    }

    @Override
    public ProjetoResponse findById(Long projetoId) {
        var foundProjeto = projetoRepository.findByIdOrElseThrow(projetoId);
        return projetoMapper.toResponse(foundProjeto);
    }

    @Override
    public void deleteById(Long projetoId) {
        var projetoToDelete = projetoRepository.findByIdOrElseThrow(projetoId);
        projetoRepository.delete(projetoToDelete);
    }

    @Override
    public ProjetoResponse updateById(ProjetoRequest projetoRequest, Long projetoId) {
        var projetoToUpdate = projetoRepository.findByIdOrElseThrow(projetoId);
        var projetoToUpdateData = projetoMapper.toModel(projetoRequest);
        BeanUtils.copyProperties(projetoToUpdateData, projetoToUpdate, "status", "dataEntrega", "id", "lider");
        var updatedProjeto = projetoRepository.save(projetoToUpdate);
        return projetoMapper.toResponse(updatedProjeto);
    }

    @Override
    public DefaultResponse markAsAceito(Long projetoId) {
        var projetoToMarkAsAceito = projetoRepository.findByIdOrElseThrow(projetoId);
        projetoToMarkAsAceito.setStatus(Status.ACEITO);
        projetoRepository.save(projetoToMarkAsAceito);
        return DefaultResponse.builder()
            .message("Projeto foi aceito com sucesso!")
            .build();
    }

    @Override
    public List<TarefaResponse> findTarefasByProjetoId(Long projetoId) {
        var foundProjeto = projetoRepository.findByIdOrElseThrow(projetoId);
        return tarefaRepository.findByProjeto(foundProjeto)
            .stream()
            .map(tarefaMapper::toResponse)
            .toList();
    }

    private Specification<Projeto> getFindAllSpec(ProjetoFindAllQueryFilter queryFilter) {
        var usuario = securityUtils.getAuthenticatedUser().getUsuario();
        if (securityUtils.isAdmin()) {
            return queryFilter.getSpec();
        } else if (securityUtils.isGerente()) {
            queryFilter.setLider(null);
            return queryFilter.getSpec().and(withLider(usuario));
        }
        queryFilter.setResponsavel(null);
        return queryFilter.getSpec().and(withResponsavel(usuario));
    }

}
