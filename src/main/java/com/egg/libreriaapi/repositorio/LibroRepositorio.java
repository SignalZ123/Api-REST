package com.egg.libreriaapi.repositorio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.Libro.LibroDarBajaDTO;
import com.egg.libreriaapi.models.Libro.LibroListDTO;
import com.egg.libreriaapi.models.Libro.LibroListarActivosDTO;
import com.egg.libreriaapi.models.Libro.LibroPorAutorDTO;
import com.egg.libreriaapi.models.Libro.LibroPorEditorialDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    /* Hacemos consultas personalizadas para recuperar lista de libros activos
     * 
     * consulta usa el PATRO DTO para crear instancias de 'LibrolistarActivoDTO'.
     * incluiran los campos necesrios de : titulo y ejemplares.
     * 
     * consulta busca libros cuyo atributo 'libroactivo' sea true
     */

    @Query("SELECT new com.egg.libreriaapi.models.Libro.LibrolistarActivoDTO(l.titulo, l.ejemplares)" + 
     "FROM Libro l WHERE l.libroActivo = true")List<LibroListarActivosDTO> encontrarActivos();
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
     
     @Query("SELECT new com.egg.libreriaapi.models.Libro.LibroListDTO(l.isbnDTO, l.tituloDTO, l.ejemplaresDTO, l.idAutor, l.idEditorial, l.libroActivoDTO)" +
     "FROM Libro l") List<LibroListDTO> listarAllLibros();

     //Consulta personalizada (JPQL(Java Persistence Query Language))
     //aqui es donde se hace el requerimiento de "Seleccionar Libros por Editoriales"
     @Query("SELECT new com.egg.libreriaapi.models.Libro.LibroPorEditorialDTO(l.idLibro, l.titulo, l.ejemplares, l.idAutor, l.idEditorial, l.libroActivo)" +
     "FROM Libro l WHERE l.editorial.idEditorial = :idEditorial") List<LibroPorEditorialDTO> listarPorEditorial(@Param("idEditorial") UUID idEditorial); 
     /*l.editorial: Relación entre la entidad Libro y la entidad Editorial
      * La entidad Libro tiene una relación (probablemente un campo como @ManyToOne) con la entidad Editorial.

      *l.editorial.idEditorial: Accede al campo idEditorial dentro de la entidad Editorial.

      *= :idEditorial: Filtra los resultados para que solo se incluyan libros cuya editorial tenga el idEditorial proporcionado como parámetro.
      
      *List<LibroPorEditorialDTO>:Retorna una lista de objetos del tipo LibroPorEditorialDTO, que contiene solo los datos necesarios de los libros.

      *@Param("idEditorial") : Vincula el parámetro de la consulta JPQL (:idEditorial) con el argumento del método UUID idEditorial.
      */

      @Query("SELECT new com.egg.libreriaapi.models.Libro.LibroPorAutorDTO(l.idLibro, l.titulo, l.ejemplares, l.idAutor, l.idEditorial, l.libroActivo)" +
      "FROM Libro l WHERE l.autor.idAutor = :idAutor") List<LibroPorAutorDTO> listarPorAutor(@Param("idAutor") UUID idAutor);
      
      @Query("SELECT new com.egg.libreriaapi.models.Libro.LibroPorAutorDTO(l.idLibro, l.titulo, l.ejemplares, l.idAutor, l.idEditorial, l.libroActivo)"+
      "FROM Libro l WHERE l.autor.idAutor = :idAutor AND l.editorial.idEditorial = :idEditorial") List<LibroPorAutorDTO> listarPorAutorYEditorial(@Param("idAutor") UUID idAutor, @Param("idEditorial") UUID idEditorial);


      //buscar por titulo---por comprobar
      @Query("SELECT new com.egg.libreriaapi.models.Libro.LibroDarBajaDTO(l.isbn, l.titulo, l.ejemplares, l.idAutor, l.idEditorial)" + 
      "FROM Libro l WHERE l.titulo = :titulo") List<LibroDarBajaDTO> buscarPorTituloLista1(@Param("titulo") String titulo);

      @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo") List<Libro> buscarPorTituloLista(@Param("titulo") String titulo);  

      @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
      Libro buscarPorTitulo(@Param("titulo") String titulo);
  
}   

