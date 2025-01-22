package com.egg.libreriaapi.repositorio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.models.Editorial.EditorialListActivosDTO;
import com.egg.libreriaapi.models.Editorial.EditorialListDTO;


public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {

    @Query("SELECT new com.libreriaapi.models.Editorial.EditorialListActivosDTO(e.idEditorial. e.nombreEditorial, e.editorialActivo)" + 
    "FROM Editorial e WHERE e.editorialActiva = true")List<EditorialListActivosDTO> listarEditorialesActivos(); 

    @Query("SELECT new com.libreriaapi.models.Editorial.EditorialListDTO(e.idEditorial. e.nombreEditorial, e.editorialActivo)" + 
    "FROM Editorial e ")List<EditorialListDTO> listarEditoriales(); 
    
}
