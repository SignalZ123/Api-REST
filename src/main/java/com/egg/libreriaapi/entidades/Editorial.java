package com.egg.libreriaapi.entidades;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_editorial")
    private UUID idEditorial;

    @Column(name = "nombre_editorial", length = 255, nullable = false)
    private String nombreEditorial;  
    
    @Column(name = "editorial_activa", nullable = false)
    private Boolean editorialActiva;


}   
