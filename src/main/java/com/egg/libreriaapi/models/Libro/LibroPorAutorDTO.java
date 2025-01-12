package com.egg.libreriaapi.models.Libro;

import java.util.UUID;

import lombok.Data;

@Data
public class LibroPorAutorDTO {
     private Long idLibro;
    private String titulo;
    private int ejemplares;
    private boolean libroActivo;
    private UUID idAutor;
    private UUID idEditorial;

    //Constructor
    public LibroPorAutorDTO(Long idBook, String title, int ejemplares, boolean libroActiv, UUID idAut, UUID idEdi){

        this.idLibro = idBook;
        this.titulo = title;
        this.ejemplares = ejemplares;
        this.libroActivo = libroActiv;
        this.idAutor = idAut;
        this.idEditorial=idEdi;

    }
    
}
