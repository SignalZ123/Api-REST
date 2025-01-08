package com.egg.libreriaapi.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.servicios.AutorServicios;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;


//controlador de Spring MVC
@RestController//indicamos que es un controlador tipo Rest
@RequestMapping("/autor")//definimos ruta a la que respondera
public class AutorControlador {
    //instanciamos al servicio, para acceder a sus metodos
    @Autowired//inyeccion de dependencias de AutorServicio
    private AutorServicios autorServicios;

    //guardar o crear ... autor
    @PostMapping("crear")//(cuando se guardan daos)
    //de tipo ResponseEntity--- permite representar la respuesta HTTP completa
    public ResponseEntity<Object> crearAutor(@RequestParam String nombre) {
        
        try {
            autorServicios.crearAutor(nombre);
            //Si la creación del autor es exitosa, devuelve una respuesta HTTP con estado 200 (OK).
            return new ResponseEntity<>("Autor creado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            //Si ocurre alguna excepción durante la creación del autor, devuelve una respuesta HTTP con estado 500 (Internal Server Error). 
            return new ResponseEntity<>("Error al crear un nuevo Autor",HttpStatus.INTERNAL_SERVER_ERROR);
        
        }
    }
    
    @GetMapping("listar")//GET (cuando se recuperan datos)
    public ResponseEntity<Object> listarAutor() {
        try {
            //equivalente a ResponseEntity<>(autorServicios.listarAutores(), HttpStatus.OK);
            return ResponseEntity.ok(autorServicios.listarAutores());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al listar los autores: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("darBajaPorId")
    public ResponseEntity<Object> darBajaAutor(@RequestParam UUID id) {
        try {
            autorServicios.bajaAutor(id);
            return new ResponseEntity<>("Autor dado de baja correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al dar de baja el autor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
    
    @PostMapping("darBajaPorNombre")
    public ResponseEntity<Object> darBajaAutorPorNombre(@RequestParam String nombre) {
        try {
            autorServicios.darBajaAutorPorNombre(nombre);
            return new ResponseEntity<>("Autor dado de baja correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al dar de baja el autor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT(que reemplaza por completo el recurso) y PATCH (modifica campos especificos que le indiques) y
    @PatchMapping("modificarAutor") // PUT(que reemplaza por completo el recurso) y PATCH (modifica campos especificos que le indiques) y
    public ResponseEntity<Object> modificarAutor(@RequestParam String nombre, @RequestParam UUID id) {
        try {
            autorServicios.modificarAutor(nombre, id);
            return ResponseEntity.ok().build();
            /*.build():
             * Finaliza el constructor y devuelve un objeto ResponseEntity sin cuerpo de respuesta.
             * equivalente a return new ResponseEntity<>(null, HttpStatus.OK);
             */
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
