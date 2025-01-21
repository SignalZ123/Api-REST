package com.egg.libreriaapi.servicios;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.models.Autor.AutorDeleteDTO;
import com.egg.libreriaapi.models.Autor.AutorListActivosDTO;
import com.egg.libreriaapi.repositorio.AutorRepositorio;



@Service
public class AutorServicios {
    
    //implementamos los metodos para crear, leer(todos y algunos
    // tambien metodos de "actualizar y eliminar" (dar de baja)

    @Autowired 
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre){

        Autor autor = new Autor();//instanciamos un objeto autor para usar sus atributos.
        autor.setNombreAutor(nombre); //seteo el atributo, con el valor recibido como parametro
        autor.setAutorActivo(true);// de alta o activo por defecto
        
        autorRepositorio.save(autor); //persiso el dato en mi DDBB
    }

    //metodo para  obtener autor por ID | leer por Id
    @Transactional
    public Autor autorPorId(UUID id) throws Exception{
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            return autor;
        }else{
            throw new Exception("Autor no encontrado");
        }
    }

    //Metodo para listar todos los autores
    @Transactional(readOnly = true)
    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList<>();

        autores= autorRepositorio.findAll();

        return autores;
    }

    //metodo modificar autor
    @Transactional
    public void modificarAutor(String nombre, UUID id){
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {//si se encuentra el objeto por id
            Autor autor = respuesta.get();
            System.out.println(autor);
            autor.setNombreAutor(nombre);

            autorRepositorio.save(autor);
        }else{
            throw new NoSuchElementException("No se encontró el autor con ID: " + id);
        }
        
    }

    //metodo para dar de baja autor / (casi como eliminar)
    @Transactional
    public void bajaAutor(UUID id){
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAutorActivo(false);
            
            autorRepositorio.save(autor);
        }
    }

    @Transactional
    public void darBajaAutorPorNombre(String nombre) throws Exception{
        Optional<Autor> respuesta = autorRepositorio.buscarAutorPorNombre(nombre);

        if (respuesta.isPresent() || respuesta != null) {
            Autor autor = respuesta.get();

            autor.setAutorActivo(false); 
            autorRepositorio.save(autor);
        }

    }

    @Transactional(readOnly = true)
    public List<AutorListActivosDTO> autoresListActivosDTO() throws Exception{
        return autorRepositorio.autoresActivos();
    }

    //Metodo para recuperar un autor //recuperamos el objeto Autor
    //esta demas 
    @Transactional(readOnly = true)
    public Autor getOne(UUID id){
        return autorRepositorio.getReferenceById(id);
    }

    @Transactional
    public void eliminarAutor(UUID idAutor){
        autorRepositorio.deleteById(idAutor);
    }
    
    @Transactional
    public void eliminarAutorDTO(AutorDeleteDTO autorDTO){
        //obtenemos el id del autor por los getter and setter declarados en el objeto 'AutorDeleteDTO' con @Data
        autorRepositorio.deleteById(autorDTO.getIdAutor());
    }

}

//En esta fase, no es necesario agregar validaciones en los métodos; es preferible enfocarse en la lógica principal del proyecto.