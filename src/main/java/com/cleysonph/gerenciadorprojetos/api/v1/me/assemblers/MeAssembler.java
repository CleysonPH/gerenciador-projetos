package com.cleysonph.gerenciadorprojetos.api.v1.me.assemblers;

import java.util.Collection;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.me.routes.MeRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.routes.ProjetoRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.routes.TarefaRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.routes.UsuarioRoutes;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeAssembler implements Assembler<MeResponse> {

    private final SecurityUtils securityUtils;

    @Override
    public MeResponse toResource(MeResponse resource) {
        resource.add(MeRoutes.getSelfLink());
        resource.add(ProjetoRoutes.getFindAllLink());
        resource.addAllIf(
            securityUtils.isAdmin() || securityUtils.isGerente(),
            List.of(
                ProjetoRoutes.getCreateLink(),
                TarefaRoutes.getCreateLink()
            )
        );
        resource.addAllIf(
            securityUtils.isAdmin(),
            List.of(
                UsuarioRoutes.getCreateLink(),
                UsuarioRoutes.getFindAllLink()
            )
        );

        return resource;
    }

    @Override
    public ResourceCollectionResponse<MeResponse> toResourceCollection(Collection<MeResponse> resourceCollection) {
        // TODO Auto-generated method stub
        return null;
    }

}
