package com.egg.libreriaapi.models.Autor;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutorListActivosDTO {

    private UUID idAutor;
    private String nombreAutor;
    private Boolean autorActivo;
    
}
