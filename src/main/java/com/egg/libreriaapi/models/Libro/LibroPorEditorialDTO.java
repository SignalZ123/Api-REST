package com.egg.libreriaapi.models.Libro;

import java.util.UUID;

import lombok.Data;

//Estrelaciona como una entidad para el Libro Repositorio haga la consulta de obtener 'Libros existentes por editorial'
//corroborar con el LibroRepositorio
@Data
public class LibroPorEditorialDTO {

  
    private Long idLibro;
    private String titulo;
    private int ejemplares;
    private Boolean libroActivo;
    private UUID idAutor;
    private UUID idEditorial;

    //Constructor
    public LibroPorEditorialDTO(Long idBook, String title, int ejemplares, Boolean libroActiv, UUID idAut, UUID idEdi){

        this.idLibro = idBook;
        this.titulo = title;
        this.ejemplares = ejemplares;
        this.libroActivo = libroActiv;
        this.idAutor = idAut;
        this.idEditorial=idEdi;

    }
    
}
