Evidencias de entrega

17. Preguntas
¿Qué es una API?
    Es una interfaz de programación de aplicaciones (Application Programming Interface) que actúa como un intermediario o puente para permitir que dos sistemas de software se comuniquen y compartan datos entre sí bajo reglas estandarizadas.

¿Qué significa consumir una API?
    Significa realizar peticiones HTTP (como GET, POST, PUT o DELETE) a los servicios exposed por la API desde una aplicación cliente (como una interfaz en Angular, un cliente móvil o Thunder Client) para leer, crear o manipular información.

¿Qué es un endpoint?
    Es la dirección URL específica exposed por la API donde el servidor recibe peticiones para realizar una acción o gestionar un recurso determinado (por ejemplo: http://localhost:8080/productos).

¿Cuál es la diferencia entre @PathVariable y @RequestParam?
    @PathVariable: Obtiene datos integrados directamente en la ruta de la URL (ejemplo: /productos/1). Se utiliza de forma obligatoria para identificar un recurso único mediante su ID.

    @RequestParam: Extrae valores pasados mediante variables de consulta (query parameters) después del signo ? (ejemplo: /productos?categoria=Hortalizas). Se utiliza para aplicar filtros opcionales, ordenamientos o paginación.

¿Qué información comunica el código 200?
    200 OK comunica que la petición HTTP fue procesada exitosamente por el servidor y que la respuesta contiene los datos solicitados (utilizado habitualmente en operaciones GET, PUT y DELETE).

¿Cuándo utilizarías 201?
    El código 201 Created debe utilizarse cuando una petición exitosa resulta directamente en la creación efectiva de un nuevo recurso en el servidor (estándar para peticiones de creación con POST).

¿Qué significa 400?
    400 Bad Request significa que el servidor no pudo procesar la solicitud porque la petición enviada por el cliente contiene datos sintácticamente incorrectos, datos faltantes o valores inválidos.

¿Qué significa 404?
    400 Bad Request significa que el servidor no pudo procesar la solicitud porque la petición enviada por el cliente contiene datos sintácticamente incorrectos, datos faltantes o valores inválidos.

¿Por qué una API debe validar los datos enviados por el cliente?
    Para garantizar la integridad y seguridad del sistema, evitando que se guarden datos corruptos, incompletos o incoherentes (como precios o cantidades negativas) en la base de datos, y para prevenir excepciones no controladas en el servidor.

¿Qué diferencia existe entre la URL de un recurso y un filtro de búsqueda?
    URL de un recurso: Apunta directamente a la identidad de una entidad en el sistema (ejemplo: /productos/5 apunta explícitamente a un recurso individual).

    Filtro de búsqueda: Se anexa al recurso base para acotar un conjunto de resultados según atributos sin modificar el recurso raíz que se consulta (ejemplo: /productos?precioMaximo=5000 consulta la lista general de productos filtrada por un criterio).
