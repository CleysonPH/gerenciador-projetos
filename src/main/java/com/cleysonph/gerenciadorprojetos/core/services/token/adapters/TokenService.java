package com.cleysonph.gerenciadorprojetos.core.services.token.adapters;

import java.time.ZonedDateTime;

public interface TokenService {

    String generateAccessToken(String subject);

    String getSubjectFromAccessToken(String accessToken);

    ZonedDateTime getExpirationFromAccessToken(String accessToken);

}
