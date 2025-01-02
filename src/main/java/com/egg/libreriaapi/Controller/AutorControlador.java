package com.egg.libreriaapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.servicios.AutorServicios;
import org.springframework.web.bind.annotation.PostMapping;

//controlador de Spring MVC
@RestController//indicamos que es un controlador tipo Rest
@RequestMapping("/autor")//definimos ruta a la que respondera
public class AutorControlador {
    //instanciamos al servicio, para acceder a sus metodos
    @Autowired//inyeccion de dependencias de AutorServicio
    private AutorServicios autorServicios;

    //guardar o crear ... autor
    @PostMapping("crear")//
    //de tipo ResponseEntity--- permite representar la respuesta HTTP completa
    public ResponseEntity<Object> crearAutor(String nombre) {
        
        try {
            autorServicios.crearAutor(nombre);
            //Si la creación del autor es exitosa, devuelve una respuesta HTTP con estado 200 (OK).
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            //Si ocurre alguna excepción durante la creación del autor, devuelve una respuesta HTTP con estado 500 (Internal Server Error). 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
        }
    }
    
}
