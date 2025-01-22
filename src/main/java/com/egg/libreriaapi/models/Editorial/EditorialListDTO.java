package com.egg.libreriaapi.models.Editorial;

import java.util.UUID;

import lombok.Data;

@Data
public class EditorialListDTO {
    private UUID idEditorial;
    private String nombreEditorial;
    private Boolean editorialActiva;
}
