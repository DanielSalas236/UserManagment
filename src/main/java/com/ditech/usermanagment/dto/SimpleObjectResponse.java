package com.ditech.usermanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@Schema(description = "Respuesta genérica de la API")
public class SimpleObjectResponse {
    @Schema(description = "Código de respuesta", example = "200")
    private int code;
    @Schema(description = "Mensaje de respuesta", example = "Operación exitosa")
    private String message;
    @Schema(description = "Datos de respuesta")
    private Object value;
}
