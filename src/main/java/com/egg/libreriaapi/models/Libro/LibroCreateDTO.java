package com.egg.libreriaapi.models.Libro;

import java.util.UUID;

import lombok.Data;

@Data//simplifica anadir los getter y setters y anade un consturctor
/*(Objeto de transferencia de datos)
 * empleado para la creacion de un nuevo libro.
 * se diferencia de la 'Entidad Libro', no requiere alamcenar el objeto completo de Autor y Editorial. Si no solo sus ID, sim embargo conserva los demas atributos.
 */

public class LibroCreateDTO {

    private Long isbn; //id del libro
    private String titulo;
    private int ejemplares;
    private UUID idAutor;
    private UUID idEditorial;
    private Boolean libroActivo;
    
}