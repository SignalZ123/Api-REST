package com.egg.libreriaapi.models.Editorial;

import java.util.UUID;

import lombok.Data;

@Data
public class EditorialDarBajaDTO {
    private UUID idEditorial;
    private String nombreEditorial;

    public EditorialDarBajaDTO(UUID idEditorial, String nombreEditorial){
        this.idEditorial=idEditorial;
        this.nombreEditorial=nombreEditorial;
    }
    
}
