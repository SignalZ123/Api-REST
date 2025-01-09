package com.egg.libreriaapi.models;

import lombok.Data;

@Data
public class LibroListarActivosDTO {

    private String titulo;
    private int ejemplares;

    //Creoamo un constructor solamente con los atributos que vamos alamacenar en el patro DTO 
    public LibroListarActivosDTO(String title, int ejemplares){
        this.titulo = title;
        this.ejemplares = ejemplares;
    }

    
}
