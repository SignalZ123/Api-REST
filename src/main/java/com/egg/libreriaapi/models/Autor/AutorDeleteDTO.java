package com.egg.libreriaapi.models.Autor;

import java.util.UUID;

import lombok.Data;

@Data
public class AutorDeleteDTO {
    private UUID idAutor;
    private String nombreAutor;
    private Boolean autorActivo;
    
    public AutorDeleteDTO(UUID idAutor, String nombreAutor, Boolean autorActivo){
        this.idAutor=idAutor;
        this.nombreAutor=nombreAutor;
        this.autorActivo = autorActivo;
    }
}
