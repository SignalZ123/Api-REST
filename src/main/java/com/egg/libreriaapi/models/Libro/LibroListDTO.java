package com.egg.libreriaapi.models.Libro;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibroListDTO {
    private Long isbnDTO;
    private String tituloDTO;
    private int ejemplaresDTO;
    private UUID idAutor;
    private UUID idEditorial;
    private Boolean libroActivoDTO;
    
}
