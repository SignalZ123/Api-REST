package com.egg.libreriaapi.servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.LibroCreateDTO;
import com.egg.libreriaapi.models.LibroListarActivosDTO;
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

    @Transactional(readOnly = true)
    public List<LibroListarActivosDTO> listarLibrosActivos(){

        return libroRepositorio.encontrarActivos();
        

        
    }
}
 