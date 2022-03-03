package com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private int status;
    private String cause;
    private ZonedDateTime timestamp;
    private String message;
    private String path;
    private Map<String, List<String>> errors;

}
