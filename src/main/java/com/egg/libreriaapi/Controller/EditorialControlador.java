package com.egg.libreriaapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.servicios.EditorialServicios;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicios editorialServicios;

    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(String nombre){
        try {
            editorialServicios.crearEditorial(nombre);
            return new ResponseEntity<>(HttpStatus.OK);            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
