package com.egg.libreriaapi.models.Editorial;

import java.util.UUID;

import lombok.Data;

@Data
public class EditorialDeleteDTO {
    private UUID idEditorial;
    private String nombreEditorial;
    private Boolean editorialActiva;

    public EditorialDeleteDTO(UUID idEditorial, String nombreEditorial, Boolean editorialActiva){
        this.idEditorial=idEditorial;
        this.nombreEditorial=nombreEditorial;
        this.editorialActiva=editorialActiva;
    }
    
}
