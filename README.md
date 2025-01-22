<h1>CHALLENGUE FORO-HUB</h1>

FORO-HUB es un proyecto desarrollado en Java con Spring Boot 3 que implementa un sistema de foros interactivo y eficiente, diseñado para destacar habilidades técnicas y prácticas modernas en desarrollo backend.

<h2>Características principales:</h2>

* Gestión de datos robusta: Uso de MySQL como base de datos relacional, con operaciones completas de CRUD manejadas por JPA y Hibernate.
* Migraciones de bases de datos: Integración de Flyway para un control eficiente de versiones.
* Productividad y limpieza en el código: Uso de Lombok para reducir código repetitivo y mejorar la legibilidad.
* Seguridad avanzada: Implementación de JWT Token para autenticación y autorización con control de acceso basado en roles.
* Arquitectura escalable: Aplicación del patrón Strategy para una lógica modular y flexible.
* Calidad de código: Cumplimiento de los principios SOLID y enfoque en buenas prácticas para garantizar mantenibilidad y extensibilidad.
* Gestión de errores: Manejo exhaustivo de excepciones para mejorar la experiencia del usuario y garantizar estabilidad.
* Prueba Realizadas: Se utiliza el programa "INSOMNIA" para hacer pruebas a la API y poder ejecutar el CRUD para cada una de las clases especificadas.
* Documentación interactiva: Uso de Springdoc OpenAPI para generar documentación precisa y navegable del API REST.

<b>Este proyecto demuestra no solo el dominio de herramientas clave en desarrollo backend, sino también la capacidad para construir sistemas seguros, escalables y bien estructurados, ideal para destacar en procesos de selección profesional.</b>

<h2>----------------------------DATOS INICIALES----------------------------</h2>

<h2>PERFIL:</h2>
 * Se inicializa con 3 perfiles:
 
	ID		NOMBRE
	--		-----------
	1		ADMIN
	2		PROFESOR
	3		ESTUDIANTE
	
	
<h2>USUARIO:</h2>
 *Se inicializa con 1 usuario para poder generar token y poder autenticarse. (La clave desencriptada es 'prueba123')
 
	ID		NOMBRE                  EMAIL			                    	CLAVE		                           	perfil_id
	--		------------	------------------------	---------------------------------------------------------		----------
	1		user_prueba1	user_prueba1@foro.hub.co	$12$dk44l7vU7aMvAaoYYgh2r.DnEvTwSSJ2Pg8TWEGRonqDjJeQWOHni			1
	
<h2>CURSOS:</h2>
 * Se inicializa con 7 cursos:
 
	ID						NOMBRE							    CATEGORIA
	--		-------------------------------------------------------------------		---------------------------------------
	1		Lógica de Programación: Sumérgete en la programación con Javascript		PRINCIPIANTE_PROGRAMACION
	2		Lógica de Programación: Explorar Funciones y Listas.		  	        PRINCIPIANTE_PROGRAMACION
	3		ChatGPT: Optimizando la calidad de los resultados.				PRINCIPIANTE_PROGRAMACION
	4		Java: Creando tu primera aplicación.				  	        JAVA_ORIENTADO_OBJETOS
	5		Java: Aplicando la orientación a objetos.			  	        JAVA_ORIENTADO_OBJETOS
	6		Java: Trabajando con almbdas, streams y spring framework.		        JAVA_Y_SPRING_FRAMEWORK
	7		Java: Persistencia de datos y consultas con Spring Data JPA.			JAVA_Y_SPRING_FRAMEWORK





<h2>----------------------REGLAS DE NEGOCIO----------------------</h2>

<h2>PERFIL:</h2>

 * No debe permitirse crear más perfiles.
 * No debe permitirse borrar perfiles.
 * No debe permitirse actualizar perfiles.
 
 
<h2>USUARIOS:</h2>

 * No se puede eliminar el usuario inicial ya que es el administrador que se requiere para generar "token" y autenticarse.
 * Todos los campos son obligatorios para registrar un Usuario nuevo (nombre, email, clave y perfil).
 * No pueden existir 2 usuarios con el mismo nombre.
 * No pueden existir 2 usuarios con el mismo email.
 * Para crearse, debe tener un perfil existente en la BD.
 * Se pueden actualizar los datos de un usuario existente.
 * Se puede borrar un usuario existente.
 * Al eliminarse se deben borrar los tópicos y respuestas asociados al usuario.
 
 
<h2>CURSOS:</h2>

 * Todos los campos son obligatorios para registrar un curso nuevo (nombre y categoria).
 * No pueden existir 2 cursos con el mismo nombre.
 * Debe obtener una categoría válida:
	(PRINCIPIANTE_PROGRAMACION,
    JAVA_ORIENTADO_OBJETOS,
    JAVA_Y_SPRING_FRAMEWORK,
    INTELIGENCIA_ARTIFICIAL_Y_JAVA,)
 * Se pueden actualizar los datos de un curso existente.
 * Se puede borrar un curso existente.
 * Al eliminarse se deben borrar los tópicos asociados al curso.


<h2>TOPICOS:</h2>

 * Todos los campos son obligatorios para registrar un tópico nuevo (Título, Mensaje, Autor(Usuario) y Curso).
 * La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).
 * El usuario ingresado debe existir en la BD.
 * El curso ingresado debe existir en la BD.
 * Se pueden actualizar los datos de un tópico existente.
 * Se puede borrar un tópico existente.
 * Al eliminarse se deben borrar las respuestas asociados al tópico.
 
<h2>RESPUESTAS:</h2>

 * Todos los campos son obligatorios para registrar una respuesta nueva (Mensaje, Tópico y Autor(Usuario).
 * El tópico ingresado debe existir en la BD.
 * El usuario ingresado debe existir en la BD.
 * Se pueden actualizar los datos de una respuesta existente.
 * Se puede borrar una respuesta existente.

