package com.egg.libreriaapi.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.libreriaapi.entidades.Editorial;


public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {
    
}
