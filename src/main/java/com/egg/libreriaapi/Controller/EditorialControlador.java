package com.egg.libreriaapi.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.models.Editorial.EditorialCreateDTO;
import com.egg.libreriaapi.models.Editorial.EditorialDarBajaDTO;
import com.egg.libreriaapi.models.Editorial.EditorialDeleteDTO;
import com.egg.libreriaapi.models.Editorial.EditorialListDTO;
import com.egg.libreriaapi.servicios.EditorialServicios;


import org.springframework.web.bind.annotation.RequestBody;


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
    @PostMapping("CrearDTO")
    public ResponseEntity<Object> crearEditorialDTO(@RequestBody EditorialCreateDTO  editorialCreateDTO) {
        
         /*
         * Este método maneja solicitudes HTTP POST para crear una nueva editorial.
         * Recibe un objeto EditorialCreateDTO en el cuerpo de la solicitud.
         * Puedes optar por diferentes enfoques para establecer el estado de alta de la
         * editorial:
         * 
         * 1. **Opción 1**: Establecer el estado de alta a TRUE mediante el constructor
         * al crear una instancia de Editorial.
         * - En este enfoque, solo se recibe el nombre en el DTO, y el estado de alta se
         * fija por defecto al crear la instancia de Editorial.
         * 
         * 2. **Opción 2**: Configurar el estado de alta manualmente al utilizar un DTO.
         * - En este caso, se recibe toda la información necesaria, incluyendo el estado
         * de alta, en el DTO, y se asigna manualmente al crear la editorial.
         * 
         * Estos enfoques permiten distintas formas de resolver el problema,
         * proporcionando flexibilidad en cómo manejar la creación y configuración de
         * las entidades.
         */

        try {
            editorialServicios.crearEditorialDTO(editorialCreateDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("listarDTO")
    public ResponseEntity<List<EditorialListDTO>> listarEditorialDTO() {
        try {
            List<EditorialListDTO> editorialList = editorialServicios.listarEditorialDTO();

            return ResponseEntity.ok(editorialList);
        } catch (Exception e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
    

    //implementando el path variable-----------------------
    @GetMapping("/ListarEditorial/{id}")
    //devolvera un objeto "ResponseEntity" que contiene una instancia de la clase 'Editorial'
    /*idEditorial se extraera de la URL(valor que se encuentre en la posicion {id}) */
    public ResponseEntity<Editorial> listarEditorialId(@PathVariable UUID idEditorial) {
        try {
            Editorial editorial = editorialServicios.getOneEdi(idEditorial);
            return ResponseEntity.ok(editorial);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("darBajaEditorial")
    public ResponseEntity<Object> darBajaEditorial(@RequestParam UUID idEditorial) {
        try {
            editorialServicios.darBajaEditorial(idEditorial);
            return ResponseEntity.ok("Editorial dado de baja");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("darBajaEditorial")
    public ResponseEntity<Object> darBajaEditorial(@RequestBody EditorialDarBajaDTO editorialDTO) {
        try {
            editorialServicios.darBajaEditorialDTO(editorialDTO);
            return ResponseEntity.ok("Editorial dado de baja");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("modificarEditorial")
    public ResponseEntity<Object> modificarEditorial(@RequestParam UUID idEditorial, @RequestParam String nuevoNombre) {
        try {
            editorialServicios.modificarEditorial(idEditorial, nuevoNombre);
            return ResponseEntity.ok("Editorial modificado correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<Object> eliminarEditorial(@RequestParam UUID id) {
        try {
            editorialServicios.eliminarEditorial(id);
            return ResponseEntity.ok("Editorial eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("eliminarDTO")
    public ResponseEntity<Object> eliminarEditorialDTO(@RequestBody EditorialDeleteDTO editorialDTO) {
        try {
            editorialServicios.eliminarEditorialDTO(editorialDTO);
            return ResponseEntity.ok("Editorial eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    
}
