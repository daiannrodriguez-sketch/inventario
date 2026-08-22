Reto 3. 

Al ejecutar .\mvnw.cmd spring-boot:run, el servidor integrado confirma su funcionamiento mediante las siguientes líneas en la consola:

Arranque de Tomcat: Tomcat started on port 8080 (http) with context path ''

Inicio exitoso: Started InventarioApplication in X.XXX seconds

Explicación del error HTTP 404:

Al ingresar a http://localhost:8080, el navegador devuelve una página con error 404 Not Found (Whitelabel Error Page). Confirma que el servidor web Tomcat está activo y escuchando en el puerto 8080. El código 404 aparece únicamente porque en esta etapa inicial aún no se ha definido ningún controlador o ruta mapeada para responder a la ruta raíz /.

Reto 4. Primer Endpoint

@RestController: Le indica a Spring Boot que la clase ProductoController funcionará como un controlador REST. Esto permite recibir peticiones HTTP y retornar las respuestas directamente en el cuerpo de la comunicación (como texto o JSON) sin requerir páginas HTML.

@GetMapping("/productos"): Mapea las solicitudes HTTP que utilicen el método GET hacia la ruta /productos.

/productos: Es la URL o endpoint expuesto (http://localhost:8080/productos) que invoca el método listarProductos() para devolver la respuesta configurada.

Reto 6. Crear el Modelo Producto

La clase Producto (ubicada en co.edu.sena.inventario.model) representa la estructura de datos o entidad principal del dominio agropecuario.

Atributos privados (id, nombre, precio, cantidad): Aplican el principio de encapsulamiento para proteger la información del objeto.

Constructor: Permite instanciar objetos Producto asignándoles sus valores iniciales.

Métodos Getters: Permiten la lectura de los atributos. Son fundamentales para que el framework (a través de la librería Jackson) pueda serializar el objeto Java y convertirlo a formato JSON al enviarlo mediante la API REST.