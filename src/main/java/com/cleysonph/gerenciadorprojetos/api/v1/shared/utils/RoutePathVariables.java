package com.cleysonph.gerenciadorprojetos.api.v1.shared.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutePathVariables {

    private Long usuarioId;
    private Long projetoId;

}
