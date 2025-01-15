package com.egg.libreriaapi.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

/*ConfiguracionCORS es una clase de configuración de Spring
 *  Las clases de configuración se utilizan para definir beans y personalizar el comportamiento de Spring.
 * 
 */
@Configuration

/*Se define una clase pública 'ConfiguracionCORS' que implementa la interfaz WebMvcConfigurer
 *  Esto significa que la clase debe proporcionar una implementación para los métodos definidos en la interfaz WebMvcConfigurer.
 */
public class ConfiguracionCORS implements WebMvcConfigurer {
    //indica que se está sobrescribiendo un método de la interfaz WebMvcConfigurer.
    @Override
    //Este método se utiliza para configurar las reglas de CORS.
    //@NonNull: Esta anotación, proveniente de la biblioteca org.springframework.lang, se utiliza para indicar que un parámetro o valor de retorno de un método no debe ser nulo.
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica CORS a todas las rutas
                .allowedOrigins("http://localhost:3000")  // Permite solicitudes de este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permite estos métodos HTTP
                .allowedHeaders("*");  // Permite cualquier cabecera
    }
}
/*RESUMEN:
 * Este código configura Spring para permitir solicitudes CORS desde la URL http://localhost:3000 a todas las rutas de la aplicación. Esto es necesario cuando se realiza una solicitud desde un navegador o una aplicación cliente que se ejecuta en un dominio diferente al del servidor.
 */
