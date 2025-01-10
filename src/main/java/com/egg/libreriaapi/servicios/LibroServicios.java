package com.egg.libreriaapi.servicios;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.Libro.LibroCreateDTO;
import com.egg.libreriaapi.models.Libro.LibroListDTO;
import com.egg.libreriaapi.models.Libro.LibroListarActivosDTO;
import com.egg.libreriaapi.repositorio.LibroRepositorio;

@Service
public class LibroServicios {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorServicios autorServicios;

    @Autowired
    private EditorialServicios editorialServicios;

    @Transactional
    public void crearLibro(Long idLibro, String titulo, int ejemplares, UUID idAutor, UUID idEditorial)throws Exception{

        try {
            Libro libroNuevo = new Libro();
            libroNuevo.setIdLibro(idLibro);
            libroNuevo.setTitulo(titulo);
            libroNuevo.setEjemplares(ejemplares);
    
            Autor autor = autorServicios.autorPorId(idAutor);
            if (autor != null) {
                libroNuevo.setAutor(autor);
            }
            Editorial editorial = editorialServicios.editorialPorId(idEditorial);
            if (editorial !=null) {
                libroNuevo.setEditorial(editorial);   
            }

            libroRepositorio.save(libroNuevo);

        } catch (Exception e) {
            throw new Exception("Error al Crear un nuevo Libro: " + e.getMessage());
        }
    }
    
    @Transactional
    public void crearLibro(LibroCreateDTO libroCreateDTO){

        Libro libroNvo = new Libro();
        //a libronvo vamos asignarle un ISBN,(que lo obtenemos de la clase libroCreateDTO)
        libroNvo.setIdLibro(libroCreateDTO.getIsbn());
        libroNvo.setTitulo(libroCreateDTO.getTitulo());
        libroNvo.setEjemplares(libroCreateDTO.getEjemplares());
        libroNvo.setLibroActivo(libroCreateDTO.isLibroActivo());

        //para los id de Autor
        Autor autor = autorServicios.getOne(libroCreateDTO.getIdAutor());
        if (autor != null) {
            libroNvo.setAutor(autor);//Establece la relación entre el libro y el autor.
        }
        //para lis id de Editorial
        Editorial editorial = editorialServicios.getOneEdi(libroCreateDTO.getIdEditorial());
        if (editorial != null) {
            libroNvo.setEditorial(editorial);
        }

        libroRepositorio.save(libroNvo);

    }
    //Listar libros activos
    @Transactional(readOnly = true)
    public List<LibroListarActivosDTO> listarLibrosActivos(){
        return libroRepositorio.encontrarActivos();
    }

    //obtener libros por Lond isbn
    @Transactional
    public Libro obtenerLibroPorId(Long idLibro) throws Exception{
        Optional<Libro> libro = libroRepositorio.findById(idLibro);

        if (libro.isPresent()) {
            return libro.get();
        }else{
            throw new Exception("no se encontro el Libro");
        }
    }

    public List<LibroListDTO> listarLibros() throws Exception{

        try {
            return libroRepositorio.findAll().stream().map(libro -> new LibroListDTO(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getEjemplares(),
                libro.getAutor().getIdAutor(),
                libro.getEditorial().getIdEditorial(),
                libro.getLibroActivo())).toList();
        } catch (Exception e) {
            throw new Exception("Error al obtener la lista de libros" + e.getMessage());
        }

    }

}
 