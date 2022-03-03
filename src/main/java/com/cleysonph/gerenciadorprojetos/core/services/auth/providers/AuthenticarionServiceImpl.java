package com.cleysonph.gerenciadorprojetos.core.services.auth.providers;

import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;
import com.cleysonph.gerenciadorprojetos.core.services.auth.adapters.AuthenticationService;
import com.cleysonph.gerenciadorprojetos.core.services.auth.dtos.AuthenticatedUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticarionServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var message = "Usuário não encontrado";
        return usuarioRepository.findByEmail(username)
            .map(AuthenticatedUser::new)
            .orElseThrow(() -> new UsernameNotFoundException(message));
    }

}
