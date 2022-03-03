package com.cleysonph.gerenciadorprojetos.api.v1.auth.services;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.CredentialsRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.TokenResponse;

public interface AuthService {

    TokenResponse authenticate(CredentialsRequest credentialsRequest);

}
