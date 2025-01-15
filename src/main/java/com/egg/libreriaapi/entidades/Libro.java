package com.egg.libreriaapi.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "libro")
public class Libro {

    @Id
    @Column(name = "id_libro", nullable = false)
    private Long idLibro;

    @Column(name = "ejemplares", length = 100)
    private int ejemplares;

    @Column(name = "libro_activo", nullable = false)
    private Boolean libroActivo;

    @Column(name = "titulo", length = 255)
    private String titulo;

    //ultima leecion añadiendo especificaciones para hacer eliminaciones, no recomendable

    /* fetch = FetchType.LAZY:
     * Este atributo determina cómo se cargarán los datos de la relación desde la base de datos. En este caso, se utiliza LAZY, lo que significa que los datos de la editorial no se cargarán de inmediato cuando se cargue un libro desde la base de datos, sino que se cargarán solo cuando sea necesario.
     * 
     * CascadeType.PERSIST, CascadeType.MERGE:
     * Establece el comportamiento de cascada para operaciones de persistencia y modificación. En este caso, indica que cuando se realicen operaciones de persistencia o modificación en un libro, estas se propagarán la editorial asociada. Sin embargo, si se elimina un libro, no se eliminará automáticamente la editorial.
    */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_editorial", nullable = false)//no puede contener valores nulos
    private Editorial editorial;
    
}
