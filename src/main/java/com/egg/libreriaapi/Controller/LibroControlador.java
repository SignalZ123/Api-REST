package com.egg.libreriaapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.LibroCreateDTO;
import com.egg.libreriaapi.models.LibroListarActivosDTO;
import com.egg.libreriaapi.servicios.LibroServicios;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicios libroServicios;

    @PostMapping("crear")
    // Si prefieres enviar el dato como JSON en el cuerpo de la solicitud, cambia la anotación @RequestParam por @RequestBody
    // Indica que los datos del cuerpo de la solicitud (en formato JSON, por ejemplo) deben ser convertidos automáticamente a un objeto de tipo LibroCreateDTO.
    public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO) {

        try {
            //Llama al método crearLibro del servicio LibroServicios, pasándole el objeto libroDTO.
            libroServicios.crearLibro(libroDTO);

            return new ResponseEntity<>("Libro creado correctamente", HttpStatus.OK);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Algun dato no es correcto o es nulo, revisar. \"}");
            //Incluye un mensaje en el cuerpo de la respuesta explicando el error.
        }
    }

    @GetMapping("listarActivos")
    public ResponseEntity<List<LibroListarActivosDTO>> ListarAutores() {
        try {
            List<LibroListarActivosDTO> librosActivos = libroServicios.listarLibrosActivos();
            return ResponseEntity.ok(librosActivos);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           
        }
    }
    
    
    
}
