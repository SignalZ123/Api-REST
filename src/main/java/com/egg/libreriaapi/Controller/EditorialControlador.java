package com.egg.libreriaapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.servicios.EditorialServicios;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicios editorialServicios;

    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(@RequestParam String nombre){
        try {
            editorialServicios.crearEditorial(nombre);
            return new ResponseEntity<>("Editorial creada correctamente: ",HttpStatus.OK);            
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear una nueva editorial: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("listar")
    public ResponseEntity<Object> listarEditorial(){
        try {
            return ResponseEntity.ok(editorialServicios.listaEditoriales());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al listar las editoriales: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
