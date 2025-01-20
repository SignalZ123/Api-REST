package com.egg.libreriaapi.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.models.Libro.LibroCreateDTO;
import com.egg.libreriaapi.models.Libro.LibroListDTO;
import com.egg.libreriaapi.models.Libro.LibroListarActivosDTO;
import com.egg.libreriaapi.models.Libro.LibroPorAutorDTO;
import com.egg.libreriaapi.models.Libro.LibroPorEditorialDTO;
import com.egg.libreriaapi.servicios.LibroServicios;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicios libroServicios;

    @PostMapping("crearDTO")
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
    ///crear nomarl
    @PostMapping("crear")
    public ResponseEntity<Object> crearLibro(@RequestBody Long isbn, @RequestBody String titulo, @RequestBody int ejemplares, @RequestBody UUID idAutor, @RequestBody UUID idEditorial){
        try {
            libroServicios.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            return new ResponseEntity<>("Libro creado correctamente: " + titulo, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear un nuevo Libro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   @GetMapping("listarActivosDTO")
    // List<LibroListarActivosDTO>: Indica que el cuerpo de la respuesta será una lista de objetos LibroListarActivosDTO.
    public ResponseEntity<List<LibroListarActivosDTO>> listarLibrosActivos() {
        try {
            List<LibroListarActivosDTO> librosActivos = libroServicios.listarLibrosActivos();
            return ResponseEntity.ok(librosActivos);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // .build(): Crea la respuesta sin un cuerpo.
        }
    }

    @GetMapping("listarTodoLibros")
    public ResponseEntity<List<LibroListDTO>>  listarLibros() {
        try {
            List<LibroListDTO> librosLista =libroServicios.listarLibros();
            return ResponseEntity.ok(librosLista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("listartodoLibrosDTO")
    public ResponseEntity<List<LibroListDTO>> listarLibrosDTO() {
        try {
            // va contener un varios objetos de tipo 'LibroLisDTO' es como decir que ba contener objetos de tipo 'Libro' ya explicado anteriormente por DTO.
            List<LibroListDTO> libroAll = libroServicios.listarLibrosDTO();
            
            return ResponseEntity.ok(libroAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //probar controlador -------------------------------
    @GetMapping("librosPorId/{idLibro}")
    public ResponseEntity<Libro> obtenerLibrosPorID(@PathVariable long idLibro) {
        try {
            Libro librosId = libroServicios.obtenerLibroPorId(idLibro);
            return ResponseEntity.ok(librosId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("listarLibrosPorAutor/{idAutor}")
    public ResponseEntity<List<LibroPorAutorDTO>> libroPoridAutor(@RequestParam UUID idAutor) {
        try {
            List<LibroPorAutorDTO> libroPoridAutor = libroServicios.listarLibrosPorAutor(idAutor);
            return ResponseEntity.ok(libroPoridAutor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("listarLibrosporEditorial/{idEditorial}")
    public ResponseEntity<List<LibroPorEditorialDTO>> listarLibroPorEditorial(@RequestParam UUID idEditorial) {
        try {
            List<LibroPorEditorialDTO> libroPorEditorial = libroServicios.listarLibrosPorEditorial(idEditorial);
            return ResponseEntity.ok(libroPorEditorial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("listarPorEditorialyAutor")
    public ResponseEntity<List<LibroPorAutorDTO>> listarLibroPorEditorialyAutor(@RequestParam UUID idAutor, @RequestParam UUID idEditorial) {
        try {
            List<LibroPorAutorDTO> libroPorEditorialyAutor = libroServicios.listarLibrosPorEditorialYLibros(idAutor, idEditorial);
            return ResponseEntity.ok(libroPorEditorialyAutor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("darBajaLibro")
    public ResponseEntity<Object> darBajaLibro(@RequestBody Long isbn) {
        try {
            
            return ResponseEntity.ok(libroServicios.darBajaLibro(isbn));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
        
    
    
    
    
}
