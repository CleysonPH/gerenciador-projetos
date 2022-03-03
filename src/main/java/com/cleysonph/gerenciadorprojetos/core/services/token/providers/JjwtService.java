package com.cleysonph.gerenciadorprojetos.core.services.token.providers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

import com.cleysonph.gerenciadorprojetos.core.services.token.adapters.TokenService;
import com.cleysonph.gerenciadorprojetos.core.services.token.exceptions.TokenServiceException;
import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JjwtService implements TokenService {

    private final JjwtConfigProperties configProperties;

    @Override
    public String generateAccessToken(String subject) {
        return generateToken(configProperties.getAccessKey(), configProperties.getAccessExpiration(), subject);
    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        return getClaims(accessToken, configProperties.getAccessKey())
            .getSubject();
    }

    @Override
    public ZonedDateTime getExpirationFromAccessToken(String accessToken) {
        return getClaims(accessToken, configProperties.getAccessKey())
            .getExpiration()
            .toInstant()
            .atZone(ZoneId.of("UTC"));
    }

    private String generateToken(String signKey, int expirationTime, String subject) {
        var issuedAt = DateTimeUtils.now();
        var expiration = issuedAt.plusSeconds(expirationTime);
        return Jwts.builder()
            .setClaims(new HashMap<String, Object>())
            .setSubject(subject)
            .setIssuedAt(Date.from(issuedAt.toInstant()))
            .setExpiration(Date.from(expiration.toInstant()))
            .signWith(SignatureAlgorithm.HS512, signKey)
            .compact();
    }

    private Claims getClaims(String token, String signKey) {
        try {
            return tryGetClaims(token, signKey);
        } catch (JwtException exception) {
            throw new TokenServiceException(exception.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String signKey) {
        return Jwts.parser()
            .setSigningKey(signKey)
            .parseClaimsJws(token)
            .getBody();
    }

}
