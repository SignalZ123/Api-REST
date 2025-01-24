 package com.egg.libreriaapi.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.models.Autor.AutorListActivosDTO;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, UUID> {


     //metedo consulta para obetener autor por nombre   
    @Query("SELECT a FROM Autor a WHERE a.nombreAutor = :nombre")
    Optional<Autor> buscarAutorPorNombre(@Param("nombre") String nombre);
    
    //aqui podemos seleccionar los atributos que queremos que aparesca como en libros activos
     @Query("SELECT new com.egg.libreriaapi.models.Autor.AutorListActivosDTO(a.idAutor, a.nombreAutor, a.autorActivo)" + 
     "FROM Autor a WHERE a.autorActivo = true")List<AutorListActivosDTO> autoresActivos();


}