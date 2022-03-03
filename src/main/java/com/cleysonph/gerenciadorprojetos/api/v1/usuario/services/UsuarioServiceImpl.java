package com.cleysonph.gerenciadorprojetos.api.v1.usuario.services;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UpdateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.mappers.UsuarioMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters.UsuarioFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse create(CreateUsuarioRequest createUsuarioRequest) {
        var usuarioToCreate = usuarioMapper.toModel(createUsuarioRequest);
        usuarioToCreate.setPassword(encryptPassword(createUsuarioRequest.getPassword()));
        var createdUsuario = usuarioRepository.save(usuarioToCreate);
        return usuarioMapper.toResponse(createdUsuario);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Page<UsuarioResponse> findAll(UsuarioFindAllQueryFilter queryFilter, Pageable pageable) {
        return usuarioRepository.findAll(queryFilter.getSpec(), pageable)
                .map(usuarioMapper::toResponse);
    }

    @Override
    public UsuarioResponse findById(Long usuarioId) {
        var foundUsuario = usuarioRepository.findByIdOrElseThrow(usuarioId);
        return usuarioMapper.toResponse(foundUsuario);
    }

    @Override
    public void deleteById(Long usuarioId) {
        var usuarioToDelete = usuarioRepository.findByIdOrElseThrow(usuarioId);
        usuarioRepository.delete(usuarioToDelete);
    }

    @Override
    public UsuarioResponse updateById(
        Long usuarioId,
        UpdateUsuarioRequest updateUsuarioRequest
    ) {
        var usuarioToUpdate = usuarioRepository.findByIdOrElseThrow(usuarioId);
        BeanUtils.copyProperties(updateUsuarioRequest, usuarioToUpdate);
        var updatedUsuario = usuarioRepository.save(usuarioToUpdate);
        return usuarioMapper.toResponse(updatedUsuario);
    }

    @Override
    public DefaultResponse markAsInativo(Long usuarioId) {
        var usuarioToMarkAsInativo = usuarioRepository.findByIdOrElseThrow(usuarioId);
        usuarioToMarkAsInativo.setStatus(Status.INATIVO);
        usuarioRepository.save(usuarioToMarkAsInativo);
        return DefaultResponse.builder()
            .message("Usuário foi inativado com sucesso!")
            .build();
    }

    @Override
    public DefaultResponse markAsAtivo(Long usuarioId) {
        var usuarioToMarkAsAtivo = usuarioRepository.findByIdOrElseThrow(usuarioId);
        usuarioToMarkAsAtivo.setStatus(Status.ATIVO);
        usuarioRepository.save(usuarioToMarkAsAtivo);
        return DefaultResponse.builder()
            .message("Usuário foi ativado com sucesso!")
            .build();
    }

}
