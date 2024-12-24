 package com.egg.libreriaapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.libreriaapi.entidades.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, String> {

    
}