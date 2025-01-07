 package com.egg.libreriaapi.repositorio;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egg.libreriaapi.entidades.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, UUID> {

 //metedo consulta para obetener autor por nombre   
 @Query("SELECT a FROM Autor a WHERE a.nombreAutor = :nombre")
 Optional<Autor> buscarAutorPorNombre(@Param("nombre") String nombre);   
}