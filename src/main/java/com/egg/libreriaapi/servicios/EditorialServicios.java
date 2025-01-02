package com.egg.libreriaapi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.repositorio.EditorialRepositorio;

import jakarta.transaction.Transactional;

@Service
public class EditorialServicios {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //crear editorial
    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();

        editorial.setNombreEditorial(nombre);
        editorial.setEditorialActiva(true);

        editorialRepositorio.save(editorial);
    }

    

    
}
