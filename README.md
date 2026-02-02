# Reto Grupo SL - Proyecto Verde 2026

Repositorio monorepo con backend Spring Boot (`appbg`) y frontend (Vite + React). Este README principal resume la estructura del proyecto, cómo ejecutar cada parte y dónde encontrar la documentación de la API.

**Estructura**
- `appbg/` - Backend Spring Boot (Java, Maven). Ver documentación de la API en [appbg/README.md](appbg/README.md).
- `frontend/` - Aplicación cliente (Vite + React).
- `bd.sql` - SQL de ejemplo / esquema.

**Backend (appbg)**
- Descripción: microservicio para administrar productos.
- Ejecutar en desarrollo (Windows):

```powershell
.\appbg\mvnw.cmd spring-boot:run
```

- Construir JAR y ejecutar:

```powershell
.\appbg\mvnw.cmd -f appbg\pom.xml clean package
java -jar appbg\target\appbg-0.0.1-SNAPSHOT.jar
```

- Configuración: `appbg/src/main/resources/application.properties` (datos de conexión a la BD, puerto, JPA).
- Documentación de la API: [appbg/README.md](appbg/README.md).

**Frontend**
- Instalar dependencias y ejecutar en desarrollo:

```bash
cd frontend
npm install
npm run dev
```

- La app cliente usa Vite y expone una UI para listar/productos y carrito.

**Base de datos**
- Archivo de ejemplo: `bd.sql`.
- El backend apunta por defecto a la configuración en `appbg/src/main/resources/application.properties`.

**Notas de desarrollo**
- El dominio contiene `Producto` en `appbg/src/main/java/com/appbg/appbg/domain/model/Producto.java` y un modelo `Moneda` en `appbg/src/main/java/com/appbg/appbg/domain/model/Moneda.java` para representar valores monetarios.
- El controlador REST básico aún puede implementarse en `appbg/src/main/java/com/appbg/appbg/infrastructure/in/controller/Controller.java`.

¿Quieres que implemente el controlador REST para `GET /api/products` o que actualice `Producto` para usar `Moneda`? Dime cuál prefieres y lo hago a continuación.

