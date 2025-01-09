package com.egg.libreriaapi.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.LibroListarActivosDTO;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    /* Hacemos consultas personalizadas para recuperar lista de libros activos
     * 
     * consulta usa el PATRO DTO para crear instancias de 'LibrolistarActivoDTO'.
     * incluiran los campos necesrios de : titulo y ejemplares.
     * 
     * consulta busca libros cuyo atributo 'libroactivo' sea true
     */

    @Query("SELECT new com.egg.libreriaapi.models.LibrolistarActivoDTO(l.titulo, l.ejemplares)" + 
     "FROM Libro l WHERE l.libriActivo = true")List<LibroListarActivosDTO> encontrarActivos(); 

    
}
