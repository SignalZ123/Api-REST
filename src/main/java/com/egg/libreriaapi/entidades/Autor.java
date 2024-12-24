package com.egg.libreriaapi.entidades;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;//para reducir la escritura de código repetitivo.
import lombok.NoArgsConstructor;

@Data//Genera automáticamente los métodos getter y setter, toString, hashCode, y equals para todos los campos de la clase.

@NoArgsConstructor//Genera un constructor sin argumentos (const. vacio). Esto es útil para frameworks como Hibernate, que requieren un constructor predeterminado para crear instancias de las entidades.

@Entity//entidad JPA, framework Hibernate o cualuqier ORM

@AllArgsConstructor//Genera automáticamente un constructor con argumentos para todos los campos de la clase.Esto simplifica la creación de objetos Autor cuando necesitas inicializar todas las propiedades.
@Table(name = "autor")

public class Autor {
    
    @Id
    //nullable = false: Indica que el campo no puede ser nulo.
    //unique = true: Garantiza que no haya valores duplicados en esta columna.
    @Column(name = "id_autor", length = 255, nullable = false, unique = true)
    private String idAutor;
    
    //nullable = false: Indica que el campo no puede ser nulo.
    @Column(name = "nombre_autor",length=255, nullable = false)
    private String nombreAutor;
    
    @Column(name = "autor_activo", nullable = false)
    private Boolean autorActivo;


    //Este constructor permite crear una instancia de Autor inicializando los campos autorActivo y nombreAutor.
    /*
    * Generación de idAutor con UUID directamente en el constructor:
    *En su lugar, el constructor genera un UUID (identificador único) directamente usando la clase java.util.UUID.
     */
    public Autor(String idAutor, Boolean autorActivo, String nombreAutor) {
        //El valor de idAutor ya no depende de un generador de Hibernate (@GenericGenerator). Genera un UUID nivel 4 y lo conviereete a String(texto)
        this.idAutor = UUID.randomUUID().toString();
        this.autorActivo = autorActivo;
        this.nombreAutor = nombreAutor;
    }

    //el construntor vacion y los getter y setter ya esta declarado en "@Data"


}