package com.egg.libreriaapi.models.Errores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//---------------------pendiente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private String message;
}
