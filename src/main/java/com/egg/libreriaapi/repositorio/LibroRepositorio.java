package com.egg.libreriaapi.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.Libro.LibroListarActivosDTO;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    /* Hacemos consultas personalizadas para recuperar lista de libros activos
     * 
     * consulta usa el PATRO DTO para crear instancias de 'LibrolistarActivoDTO'.
     * incluiran los campos necesrios de : titulo y ejemplares.
     * 
     * consulta busca libros cuyo atributo 'libroactivo' sea true
     */

    @Query("SELECT new com.egg.libreriaapi.models.LibrolistarActivoDTO(l.titulo, l.ejemplares)" + 
     "FROM Libro l WHERE l.libroActivo = true")List<LibroListarActivosDTO> encontrarActivos(); 

}
/* --new com.egg.libreriaapi.models.LibrolistarActivoDTO : Esta sintaxis crea directamente una instancia de la clase DTO (Data Transfer Object) llamada LibrolistarActivoDTO.
 * 
 * l.titulo, l.ejemplares: Se pasan como parámetros al constructor de LibrolistarActivoDTO. Esto implica que la clase LibrolistarActivoDTO debe tener un constructor que reciba estos dos argumentos en el orden exacto.
 * 
 * FROM Libro l 
 * Indica que se está consultando la entidad Libro (una clase anotada con @Entity en JPA).
 * l: Es un alias para referirse a la entidad dentro de la consulta.
 * 
 * WHERE l.libroActivo = true
 * Filtra los resultados para incluir solo los libros donde el campo libriActivo sea verdadero.
 * 
 * List<LibroListarActivosDTO> : La consulta devuelve una lista de objetos de tipo LibroListarActivosDTO.
 * 
 * 
 * "encontrarActivos": Es el nombre del método en el repositorio que ejecutará esta consulta.
*/
