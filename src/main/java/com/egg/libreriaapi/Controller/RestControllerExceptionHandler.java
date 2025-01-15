package com.egg.libreriaapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.egg.libreriaapi.models.Errores.ErrorMessage;

@RestController//controlador REST (Representational State Transfer) en Spring.
public class RestControllerExceptionHandler {

    // indica que el método responseStatusExceptionHandler se encargará de manejar excepciones del tipo ResponseStatusException.
    //ResponseStatusException es una excepción definida en Spring que se utiliza para devolver respuestas HTTP con códigos de estado específicos.
    @ExceptionHandler(ResponseStatusException.class)

    public ResponseEntity<ErrorMessage> responseStatusExceptionHandler(ResponseStatusException ex){
        /*Crea un objeto ErrorMessage
         * Extrae el código de estado de la excepción (ex.getStatusCode().value()) y el mensaje de error (ex.getMessage()) y los asigna al objeto ErrorMessage.
         */
        ErrorMessage message = new ErrorMessage(ex.getStatusCode().value(), ex.getMessage());

        //devuelve una respuesta HTTP con el código de estado de la excepción y el objeto ErrorMessage en el cuerpo de la respuesta.    
        return new ResponseEntity<>(message, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    //Este método se encargará de manejar cualquier otra excepción que no sea manejada por el anterior método.
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex){
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
