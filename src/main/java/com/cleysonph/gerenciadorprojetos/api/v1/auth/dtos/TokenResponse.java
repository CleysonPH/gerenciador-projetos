package com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String access;
    private ZonedDateTime expiresAt;
    private String tokenType;

}
