package com.egg.libreriaapi.models.Libro;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibroDarBajaDTO {
    private Long isbn;
    private String titulo;
    private int ejemplares;
    private UUID idAutor;
    private UUID idEditorial;
    
    public  LibroDarBajaDTO(Long isbn, String titulo, int ejemplares, UUID idAutor, UUID idEditorial){
        this.isbn = isbn;
        this.titulo = titulo;
        this.ejemplares=ejemplares;
        this.idAutor=idAutor;
        this.idEditorial=idEditorial;
    }
}
