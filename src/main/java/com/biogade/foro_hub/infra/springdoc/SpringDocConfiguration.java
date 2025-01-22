package com.biogade.foro_hub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("CHALLENGUE: FORO HUB - DESARROLLADO POR DAVID SILVA")
                        .description("""
                                FORO-HUB es un proyecto desarrollado en Java con Spring Boot 3 que implementa un sistema de foros interactivo y eficiente, diseñado para destacar habilidades técnicas y prácticas modernas en desarrollo backend.
                                
                                Características principales:\n
                                
                                -Gestión de datos robusta: Uso de MySQL como base de datos relacional, con operaciones completas de CRUD manejadas por JPA y Hibernate.\n
                                -Migraciones de bases de datos: Integración de Flyway para un control eficiente de versiones.\n
                                -Productividad y limpieza en el código: Uso de Lombok para reducir código repetitivo y mejorar la legibilidad.\n
                                -Seguridad avanzada: Implementación de JWT Token para autenticación y autorización con control de acceso basado en roles.\n
                                -Arquitectura escalable: Aplicación del patrón Strategy para una lógica modular y flexible.\n
                                -Calidad de código: Cumplimiento de los principios SOLID y enfoque en buenas prácticas para garantizar mantenibilidad y extensibilidad.\n
                                -Gestión de errores: Manejo exhaustivo de excepciones para mejorar la experiencia del usuario y garantizar estabilidad.\n
                                -Prueba Realizadas: Se utiliza el programa "INSOMNIA" para hacer pruebas a la API y poder ejecutar el CRUD para cada una de las clases especificadas.\n
                                -Documentación interactiva: Uso de Springdoc OpenAPI para generar documentación precisa y navegable del API REST.\n\n
                                
                                Este proyecto demuestra no solo el dominio de herramientas clave en desarrollo backend, sino también la capacidad para construir sistemas seguros, escalables y bien estructurados, ideal para destacar en procesos de selección profesional.\n
                                """)
                        .contact(new Contact()
                                .name("David Silva")
                                .email("davidsn97@hotmail.com"))
                        .license(new License()
                                .name("Linkedin")
                                .url("https://www.linkedin.com/in/david-silva-nunez/")));
    }

}