# Documentation API

Este documento describe la API mínima del microservicio `appbg` (backend Spring Boot), cómo ejecutarla y los contratos JSON esperados.

**Descripción**
- Servicio para administrar productos (listado, detalle y gestión básica de stock).

**Cómo ejecutar (desarrollo)**
- Usando el wrapper de Maven (Windows):

```powershell
.\mvnw.cmd spring-boot:run
```

- O construir el JAR y ejecutarlo:

```powershell
.\mvnw.cmd clean package
java -jar target\appbg-0.0.1-SNAPSHOT.jar
```

La aplicación levanta por defecto en el puerto `8080`. La configuración de conexión a la base de datos está en `src/main/resources/application.properties`.

**Endpoints (propuestos / documentados)**

- `GET /api/products`
	- Descripción: lista todos los productos.
	- Respuesta: `200 OK` con un arreglo de productos.
	- Ejemplo de respuesta:

```json
[
	{
		"id": 1,
		"nombre": "Jugo de Naranja",
		"precio": 3500.00,
		"cantidadDisponible": 10
	},
	{
		"id": 2,
		"nombre": "Galletas",
		"precio": 2500.00,
		"cantidadDisponible": 5
	}
]
```

- `GET /api/products/{id}` (recomendado)
	- Descripción: obtiene un producto por su `id`.
	- Respuesta: `200 OK` con el objeto `Producto`, o `404 Not Found` si no existe.

**Modelo `Producto`**
- `id` (Long): identificador.
- `nombre` (String): nombre del producto.
- `precio` (Decimal / BigDecimal): precio unitario.
- `cantidadDisponible` (Integer): stock disponible.

Ejemplo de objeto:

```json
{
	"id": 1,
	"nombre": "Café",
	"precio": 4500.50,
	"cantidadDisponible": 20
}
```

**Códigos de estado comunes**
- `200 OK` — petición exitosa.
- `201 Created` — recurso creado (POST).
- `400 Bad Request` — datos inválidos.
- `404 Not Found` — recurso no encontrado.
- `500 Internal Server Error` — error del servidor.

**Notas de implementación**
- El paquete de dominio contiene `Producto` en `src/main/java/com/appbg/appbg/domain/model/Producto.java`.
- Actualmente el controlador REST no está implementado (`Controller.java`). Implementar rutas bajo `/api` y usar `ProductUseCase` para la lógica.
- Si se desea usar el modelo `Moneda` creado en `domain/model/Moneda.java`, se puede cambiar el tipo `precio` en `Producto` de `BigDecimal` a `Moneda` y adaptar los mapeos DTO.

Si quieres, puedo:
- Implementar el controlador REST básico para listar productos.
- Actualizar `Producto` para usar `Moneda` y ajustar DTOs.

Fin de la documentación inicial.


