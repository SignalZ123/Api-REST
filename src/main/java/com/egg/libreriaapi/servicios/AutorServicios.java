package com.egg.libreriaapi.servicios;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
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
    public Autor autorPorId(String id) throws Exception{
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
    public void modificarAutor(String nombre, String id){
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {//si se encuentra el objeto por id
            Autor autor = respuesta.get();
            autor.setNombreAutor(nombre);

            autorRepositorio.save(autor);
        }
    }

    //metodo para dar de baja autor / (casi como eliminar)
    @Transactional
    public void bajaAutor(String id){
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

    //Metodo para recuperar un autor //recuperamos el objeto Autor
    //esta demas 
    @Transactional(readOnly = true)
    public Autor getOne(String id){
        return autorRepositorio.getReferenceById(id);
    }

}

//En esta fase, no es necesario agregar validaciones en los métodos; es preferible enfocarse en la lógica principal del proyecto.