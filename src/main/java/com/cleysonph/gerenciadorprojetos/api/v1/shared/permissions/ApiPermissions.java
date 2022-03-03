package com.cleysonph.gerenciadorprojetos.api.v1.shared.permissions;

import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiPermissions {

    private final SecurityUtils securityUtils;
    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    public boolean isLiderOfProjeto(Long projetoId) {
        return projetoRepository.existsByIdAndLiderEmail(
            projetoId,
            securityUtils.getAuthenticatedName()
        );
    }

    public boolean isResponsavelOfProjeto(Long projetoId) {
        return projetoRepository.existsByIdAndResponsavelEmail(
            projetoId,
            securityUtils.getAuthenticatedName()
        );
    }

    public boolean isLiderOfTarefa(Long tarefaId) {
        return tarefaRepository.existsByIdAndProjetoLiderEmail(
            tarefaId,
            securityUtils.getAuthenticatedName()
        );
    }

    public boolean isResponsavelOfTarefa(Long tarefaId) {
        return tarefaRepository.existsByIdAndProjetoResponsavelEmail(
            tarefaId,
            securityUtils.getAuthenticatedName()
        );
    }

}
