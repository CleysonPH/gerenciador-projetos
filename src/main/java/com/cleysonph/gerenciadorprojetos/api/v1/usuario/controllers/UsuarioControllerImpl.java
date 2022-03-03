package com.cleysonph.gerenciadorprojetos.api.v1.usuario.controllers;

import javax.validation.Valid;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.PagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UpdateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters.UsuarioFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.routes.UsuarioRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.services.UsuarioService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;
    private final Assembler<UsuarioResponse> usuarioAssembler;
    private final PagedAssembler<UsuarioResponse> usuarioPagedAssembler;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(UsuarioRoutes.CREATE_URI)
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioResponse create(@RequestBody @Valid CreateUsuarioRequest createUsuarioRequest) {
        var usuario = usuarioService.create(createUsuarioRequest);
        return usuarioAssembler.toResource(usuario);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(UsuarioRoutes.FIND_ALL_URI)
    public ResourceCollectionResponse<UsuarioResponse> findAll(
        UsuarioFindAllQueryFilter queryFilter,
        Pageable pageable
    ) {
        var usuarios = usuarioService.findAll(queryFilter, pageable);
        return usuarioPagedAssembler.toResourceCollection(usuarios, usuarioAssembler);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(UsuarioRoutes.FIND_BY_ID_URI)
    public UsuarioResponse findById(@PathVariable Long usuarioId) {
        var usuario = usuarioService.findById(usuarioId);
        return usuarioAssembler.toResource(usuario);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(UsuarioRoutes.DELETE_BY_ID_URI)
    public ResponseEntity<Void> deleteById(@PathVariable Long usuarioId) {
        usuarioService.deleteById(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(UsuarioRoutes.UPDATE_BY_ID_URI)
    public UsuarioResponse updateById(
        @PathVariable Long usuarioId,
        @RequestBody @Valid UpdateUsuarioRequest updateUsuarioRequest
    ) {
        var usuario = usuarioService.updateById(usuarioId, updateUsuarioRequest);
        return usuarioAssembler.toResource(usuario);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(UsuarioRoutes.INATIVAR_BY_ID_URI)
    public DefaultResponse markAsInativo(@PathVariable Long usuarioId) {
        return usuarioService.markAsInativo(usuarioId);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(UsuarioRoutes.ATIVAR_BY_ID_URI)
    public DefaultResponse markAsAtivo(@PathVariable Long usuarioId) {
        return usuarioService.markAsAtivo(usuarioId);
    }

}
