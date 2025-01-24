package com.egg.libreriaapi.servicios;


import java.util.ArrayList;
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
import com.egg.libreriaapi.models.Libro.LibroDarBajaDTO;
import com.egg.libreriaapi.models.Libro.LibroListDTO;
import com.egg.libreriaapi.models.Libro.LibroListarActivosDTO;
import com.egg.libreriaapi.models.Libro.LibroPorAutorDTO;
import com.egg.libreriaapi.models.Libro.LibroPorEditorialDTO;
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
        libroNvo.setLibroActivo(libroCreateDTO.getLibroActivo());

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
    //Listar libros activos Usando el DTO
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

    // ddevuelve una 'Lista' de objetos de tipo 'LibroListDTO'
    @Transactional(readOnly = true)
    public List<LibroListDTO> listarLibros() throws Exception{

        try {
            /*.stream(): Convierte la lista de libros en un flujo, lo que permite aplicar operaciones secuenciales. 
             * .map(...): Transforma cada elemento del flujo (cada libro) en un nuevo objeto LibroListDTO.
            */
            return libroRepositorio.findAll().stream().map(libro -> new LibroListDTO(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getEjemplares(),
                libro.getAutor().getIdAutor(),
                libro.getEditorial().getIdEditorial(),
                libro.getLibroActivo())).toList();//Convierte el flujo resultante nuevamente en una lista.
        } catch (Exception e) {
            throw new Exception("Error al obtener la lista de libros" + e.getMessage());
        }

    }

    // Listar libros usando el Repositorio donde se declaro la consulta requerida
    @Transactional(readOnly = true)
    public List<LibroListDTO> listarLibrosDTO(){
        return libroRepositorio.listarAllLibros();
    }

    @Transactional(readOnly = true)
    public List<LibroPorAutorDTO> listarLibrosPorAutor(UUID idAutor){
        return libroRepositorio.listarPorAutor(idAutor);
    }

    @Transactional(readOnly = true)
    public List<LibroPorEditorialDTO> listarLibrosPorEditorial(UUID idEditorial){
        return libroRepositorio.listarPorEditorial(idEditorial);
    }

    @Transactional(readOnly = true)
    public List<LibroPorAutorDTO> listarLibrosPorEditorialYLibros(UUID idAutor, UUID idEditorial){
        return libroRepositorio.listarPorAutorYEditorial(idAutor, idEditorial);
    }
    //-------------Dar de baja libro
    @Transactional
    public void darBajaLibro(Long isbn)throws Exception{
        Optional<Libro> rptaLibro = libroRepositorio.findById(isbn);

        if (rptaLibro.isPresent()) {
            Libro libroBaja = rptaLibro.get();
            libroBaja.setLibroActivo(false);
            
            libroRepositorio.save(libroBaja);
        }else{
            throw new Exception("No se encontro el libro");
        }
    }

    //Dar baja Libro por titulo ----
    @Transactional
    public List<LibroDarBajaDTO> darBajaLibroPorTituloDTO(String titulo)throws Exception{
        List<Libro> librosEcontrado= libroRepositorio.buscarPorTituloLista(titulo);

        if (librosEcontrado == null || librosEcontrado.isEmpty()) {
            throw new Exception("No se encontró ningun libro con el titulo proporcionado");
        }

        List<LibroDarBajaDTO> libroDTO = new ArrayList<>();
        for (Libro libro : librosEcontrado) {
            libro.setLibroActivo(false);
            libroRepositorio.save(libro);

            libroDTO.add(new LibroDarBajaDTO(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getEjemplares(),
                libro.getAutor().getIdAutor(),
                libro.getEditorial().getIdEditorial()
            ));
        }
        return libroDTO;
    }
    @Transactional
    public void darBajaLibroPorTitulo(String titulo) throws Exception {
        Libro libro = libroRepositorio.buscarPorTitulo(titulo);
        if (libro != null) {
            libro.setLibroActivo(false);
            libroRepositorio.save(libro);
        } else {
            throw new Exception("No se encontro el libro");
        }
    }

    @Transactional
    public void actualizarLibro(Long idLibro, String titulo, int ejemplares, UUID idAutor, UUID idEditorial) throws Exception{
        try {
            Optional<Libro> libroEncontrado = libroRepositorio.findById(idLibro);
            if (libroEncontrado.isPresent()) {
                Libro libroActualizado = libroEncontrado.get();

                libroActualizado.setTitulo(titulo);
                libroActualizado.setEjemplares(ejemplares);
                
                Autor autor = autorServicios.getOne(idAutor);
                libroActualizado.setAutor(autor);

                Editorial editorial = editorialServicios.getOneEdi(idEditorial);
                libroActualizado.setEditorial(editorial);

                libroRepositorio.save(libroActualizado);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar el libro: " + e.getMessage());
        }
    }

    //No recomendado
    @Transactional
    public void eliminarLibro(Long isbn){
        libroRepositorio.deleteById(isbn);
    }



}
 