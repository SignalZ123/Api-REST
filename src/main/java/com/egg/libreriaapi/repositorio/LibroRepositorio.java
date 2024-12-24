package com.egg.libreriaapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.libreriaapi.entidades.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    
}
