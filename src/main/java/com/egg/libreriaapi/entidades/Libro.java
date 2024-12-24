package com.egg.libreriaapi.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

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

    
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
    
}
