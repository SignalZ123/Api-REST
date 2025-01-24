package com.egg.libreriaapi.models.Editorial;

import lombok.Data;

@Data
public class EditorialCreateDTO {
    private String nombreEditorialDTO;
    private Boolean editorialActivaDTO;

    //Defino que el atributo editorialActiva se inicializa con el valor por defecto "false", por lo tanto
    // se debe recibir el valor en el JSON en caso de querer que sea "true"

    
}
