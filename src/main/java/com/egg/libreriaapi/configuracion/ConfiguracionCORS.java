package com.egg.libreriaapi.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//---------------pendiente---------------
@Configuration
public class ConfiguracionCORS implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica CORS a todas las rutas
                .allowedOrigins("http://localhost:3000")  // Permite solicitudes de este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permite estos métodos HTTP
                .allowedHeaders("*");  // Permite cualquier cabecera
    }
}
