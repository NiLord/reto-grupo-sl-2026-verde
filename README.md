[2:05 PM, 2/2/2026] +507 6386-6959: jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres
[2:06 PM, 2/2/2026] +507 6386-6959: spring.application.name=appbg
spring.datasource.url=jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=carrito00Verde
spring.datasource.driver-class-name=org.postgresql.Driver
[2:06 PM, 2/2/2026] +507 6386-6959: spring.application.name=appbg
spring.datasource.url=jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=carrito00Verde
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration

server.port=8080
[2:11 PM, 2/2/2026] +507 6386-6959: CREATE TABLE producto (
id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
precio DECIMAL(10,2) NOT NULL,
cantidad_disponible INTEGER NOT NULL DEFAULT 0,
);

CREATE TABLE denominacion (
id BIGSERIAL PRIMARY KEY,
valor DECIMAL(10,2) NOT NULL, -- 0.50, 1.00, 5.00, 10.00, 20.00, etc.
tipo VARCHAR(20) NOT NULL, -- "MONEDA" o "BILLETE"
cantidad_disponible INTEGER NOT NULL DEFAULT 0
);
[3:56 PM, 2/2/2026] +507 6386-6959: <div align="center">

# 🛒 Máquina Expendedora Inteligente

### Sistema de Venta y Gestión de Cambio Automatizado

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-19.2.0-blue.svg)](https://reactjs.org/)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

_Proyecto Verde - Reto Grupo SL 2026_

[Características](#-características-principales) •
[Arquitectura](#-arquitectura-del-sistema) •
[Tecnologías](#-stack-tecnológico) •
[Instalación](#-instalación) •
[API](#-documentación-api)

</div>

---

## 📋 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Características Principales](#-características-principales)
- [Arquitectura del Sistema](#-arquitectura-del-sistema)
- [Stack Tecnológico](#-stack-tecnológico)
- [Diseño de Base de Datos](#-diseño-de-base-de-datos)
- [Patrones de Diseño](#-patrones-de-diseño-implementados)
- [Principios SOLID](#-principios-solid)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Documentación API](#-documentación-api)
- [Estructura del Proyecto](#-estructura-del-proyecto)

---

## 🎯 Descripción General

Sistema integral de _máquina expendedora inteligente_ que simula operaciones de venta con gestión automática de inventario, carrito de compras y cálculo de cambio optimizado. El proyecto implementa un backend robusto con _arquitectura hexagonal_ y un frontend moderno y reactivo.

### ✨ Valor del Proyecto

- 🎨 _Interfaz Intuitiva_: Experiencia de usuario fluida con React y TailwindCSS
- 🔐 _Backend Escalable_: Arquitectura limpia que separa lógica de negocio de infraestructura
- 💰 _Algoritmo de Cambio Inteligente_: Greedy algorithm para optimizar entrega de denominaciones
- 📊 _Gestión de Inventario en Tiempo Real_: Control automático de stock de productos y denominaciones
- 🔄 _Sistema de Carrito Persistente_: Gestión completa del ciclo de compra

---

## 🚀 Características Principales

### 🎫 Gestión de Productos

- ✅ Catálogo de productos con precios y disponibilidad
- ✅ Control automático de stock
- ✅ Validación de inventario antes de venta

### 🛒 Carrito de Compras

- ✅ Agregar/eliminar productos
- ✅ Actualización de cantidades
- ✅ Cálculo automático de totales
- ✅ Validación de stock disponible

### 💵 Sistema de Pago

- ✅ Aceptación de múltiples denominaciones (monedas y billetes)
- ✅ Cálculo automático de cambio
- ✅ Algoritmo greedy para optimizar entrega de cambio
- ✅ Validación de disponibilidad de denominaciones
- ✅ Gestión inteligente de inventario de efectivo

### 📈 Monitoreo en Tiempo Real

- ✅ Visualización de stock de productos
- ✅ Visualización de disponibilidad de denominaciones
- ✅ Feedback inmediato de operaciones

---

## 🏗️ Arquitectura del Sistema

### Arquitectura Hexagonal (Ports & Adapters)

El backend implementa _Arquitectura Hexagonal, también conocida como \*\*Ports and Adapters_, que permite:

┌─────────────────────────────────────────────────────────────┐
│ FRONTEND │
│ React + Vite + TailwindCSS │
│ DaisyUI Components │
└────────────────────┬────────────────────────────────────────┘
│ HTTP/REST
▼
┌─────────────────────────────────────────────────────────────┐
│ INFRASTRUCTURE LAYER │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │
│ │ Controllers │ │ DTOs │ │ Mappers │ │
│ │ (in/web) │ │ │ │ │ │
│ └──────┬───────┘ └──────────────┘ └──────────────┘ │
│ │ │
│ ▼ │
│ ┌──────────────────────────────────────────────┐ │
│ │ REST API Controllers │ │
│ │ ProductoController | CarritoController │ │
│ │ PagoController │ │
│ └──────────────────┬───────────────────────────┘ │
└─────────────────────┼────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────┐
│ APPLICATION LAYER │
│ ┌──────────────────────────────────────────────┐ │
│ │ Use Case Services │ │
│ │ ProductService | CarritoService │ │
│ │ PagoService │ │
│ └──────────────────┬───────────────────────────┘ │
└─────────────────────┼────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────┐
│ DOMAIN LAYER │
│ ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ │
│ │ Models │ │ Ports │ │ Enums │ │
│ │ (Entities) │ │ (Interfaces)│ │ │ │
│ └─────────────┘ └─────────────┘ └─────────────┘ │
│ │
│ • Producto • ProductUseCase • TipoDenominacion │
│ • Carrito • CarritoUseCase │
│ • Denominacion • PagoUseCase │
│ • ItemCarrito • ProductoRepository │
│ • ResultadoPago • DenominacionRepository │
│ • Moneda │
└─────────────────────┬────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────┐
│ INFRASTRUCTURE LAYER │
│ (out/persistence) │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │
│ │ Adapters │ │ Entities │ │ Repositories │ │
│ │ │ │ (@Entity) │ │ (JPA) │ │
│ └──────┬───────┘ └──────────────┘ └──────────────┘ │
│ │ │
│ ▼ │
│ ┌──────────────────────────────────────────────┐ │
│ │ JPA Repository Implementations │ │
│ │ ProductoJpaRepository │ │
│ │ DenominacionJpaRepository │ │
│ └──────────────────┬───────────────────────────┘ │
└─────────────────────┼────────────────────────────────────────┘
│
▼
┌──────────────┐
│ PostgreSQL │
│ Database │
└──────────────┘

### Ventajas de la Arquitectura

- ✅ _Independencia del Framework_: La lógica de negocio no depende de Spring Boot
- ✅ _Testeable_: Cada capa puede probarse de forma aislada
- ✅ _Mantenible_: Cambios en infraestructura no afectan el dominio
- ✅ _Escalable_: Fácil agregar nuevos adaptadores (GraphQL, gRPC, etc.)
- ✅ _Flexible_: Cambio de base de datos sin modificar lógica de negocio

---

## 💻 Stack Tecnológico

### Backend

| Tecnología        | Versión | Propósito                       |
| ----------------- | ------- | ------------------------------- |
| _Java_            | 21      | Lenguaje de programación        |
| _Spring Boot_     | 4.0.2   | Framework principal             |
| _Spring Data JPA_ | 4.0.2   | Persistencia y ORM              |
| _Spring Web MVC_  | 4.0.2   | REST API                        |
| _PostgreSQL_      | Latest  | Base de datos relacional        |
| _Lombok_          | Latest  | Reducción de código boilerplate |
| _Maven_           | 3.9.12  | Gestión de dependencias         |

### Frontend

| Tecnología    | Versión | Propósito                    |
| ------------- | ------- | ---------------------------- |
| _React_       | 19.2.0  | Biblioteca UI                |
| _Vite_        | 7.2.4   | Build tool y dev server      |
| _TailwindCSS_ | 4.1.18  | Framework CSS utility-first  |
| _DaisyUI_     | 5.5.16  | Componentes UI pre-diseñados |
| _ESLint_      | 9.39.1  | Linter de código             |

---

## 🗄️ Diseño de Base de Datos

### Diagrama Entidad-Relación

┌─────────────────────────────┐
│ PRODUCTO │
├─────────────────────────────┤
│ 🔑 id (BIGSERIAL) │
│ 📝 nombre (VARCHAR 100) │
│ 💰 precio (DECIMAL 10,2) │
│ 📦 cantidad_disponible (INT)│
└─────────────────────────────┘

┌─────────────────────────────┐
│ DENOMINACION │
├─────────────────────────────┤
│ 🔑 id (BIGSERIAL) │
│ 💵 valor (DECIMAL 10,2) │
│ 🏷️ tipo (VARCHAR 20) │
│ - MONEDA │
│ - BILLETE │
│ 🔢 cantidad_disponible (INT)│
└─────────────────────────────┘

### Modelo de Datos

#### Tabla: producto

Almacena el catálogo de productos disponibles en la máquina expendedora.

| Campo               | Tipo          | Descripción              |
| ------------------- | ------------- | ------------------------ |
| id                  | BIGSERIAL     | Identificador único (PK) |
| nombre              | VARCHAR(100)  | Nombre del producto      |
| precio              | DECIMAL(10,2) | Precio unitario          |
| cantidad_disponible | INTEGER       | Stock disponible         |

#### Tabla: denominacion

Gestiona el inventario de efectivo (monedas y billetes).

| Campo               | Tipo          | Descripción              |
| ------------------- | ------------- | ------------------------ |
| id                  | BIGSERIAL     | Identificador único (PK) |
| valor               | DECIMAL(10,2) | Valor de la denominación |
| tipo                | VARCHAR(20)   | MONEDA o BILLETE         |
| cantidad_disponible | INTEGER       | Cantidad en inventario   |

### Datos Iniciales

_Productos:_

- Café Americano: $2.50
- Barra de Proteína: $3.75
- Jugo de Naranja: $2.25
- Sándwich de Jamón: $4.50
- Papas Fritas: $1.50
- Galletas de Avena: $1.80

_Denominaciones:_

- Monedas: $0.01, $0.05, $0.10, $0.25, $0.50
- Billetes: $1.00, $2.00, $5.00, $10.00, $20.00, $50.00, $100.00

---

## 🎨 Patrones de Diseño Implementados

### 1. _Repository Pattern_ 🗂️

Abstracción de la capa de persistencia.

java
// Port (Domain)
public interface ProductoRepository {
List<Producto> listarProductos();
Optional<Producto> buscarPorId(Long id);
}

// Adapter (Infrastructure)
@Repository
public class ProductoAdapter implements ProductoRepository {
private final ProductoJpaRepository jpaRepository;
// Implementación...
}

_Ventajas:_

- ✅ Desacoplamiento de la lógica de negocio y persistencia
- ✅ Fácil cambio de proveedor de datos
- ✅ Testeable con mocks

### 2. _Use Case Pattern_ 🎯

Encapsulación de lógica de negocio en casos de uso específicos.

java
public interface CarritoUseCase {
Carrito agregarProducto(Long productoId, Integer cantidad);
Carrito actualizarCantidad(Long productoId, Integer cantidad);
Carrito removerProducto(Long productoId);
Carrito obtenerCarrito();
void limpiarCarrito();
}

_Ventajas:_

- ✅ Separación clara de responsabilidades
- ✅ Código reutilizable
- ✅ Fácil de mantener y extender

### 3. _Service Layer Pattern_ 🔧

Implementación de la lógica de aplicación.

java
@Service
public class PagoService implements PagoUseCase {
private final DenominacionRepository denominacionRepository;
private final CarritoUseCase carritoUseCase;

    // Lógica de negocio compleja...

}

_Ventajas:_

- ✅ Centraliza la lógica de negocio
- ✅ Transaccionalidad
- ✅ Orquestación de múltiples operaciones

### 4. _DTO Pattern_ 📦

Transferencia de datos entre capas.

java
public class PagoRequest {
private Map<Long, Integer> denominacionesInsertadas;
// Getters y Setters...
}

_Ventajas:_

- ✅ Desacoplamiento de modelos de dominio
- ✅ Validación de datos de entrada
- ✅ Control de exposición de información

### 5. _Dependency Injection_ 💉

Inyección de dependencias con Spring.

java
@RestController
@RequestMapping("/carrito")
public class CarritoController {
private final CarritoUseCase carritoUseCase;

    // Spring inyecta automáticamente la dependencia
    public CarritoController(CarritoUseCase carritoUseCase) {
        this.carritoUseCase = carritoUseCase;
    }

}

_Ventajas:_

- ✅ Bajo acoplamiento
- ✅ Alto cohesión
- ✅ Facilita testing con mocks

### 6. _Value Object Pattern_ 💎

Objetos inmutables que encapsulan valores.

java
public class Moneda {
private BigDecimal cantidad;
private String codigo; // "USD", "COP", etc.

    public Moneda sumar(Moneda otra) {
        checkCurrencyMatch(otra);
        return new Moneda(this.cantidad.add(otra.cantidad), this.codigo);
    }

}

_Ventajas:_

- ✅ Inmutabilidad
- ✅ Validación de reglas de negocio
- ✅ Expresividad del código

### 7. _Greedy Algorithm_ 🎲

Algoritmo para optimizar el cálculo de cambio.

java
private List<DenominacionCambio> calcularCambio(
BigDecimal cambio, List<Denominacion> denominaciones) {

    // Ordenar de mayor a menor
    denoms.sort((a, b) -> b.getValor().compareTo(a.getValor()));

    // Seleccionar la mayor denominación posible en cada iteración
    for (Denominacion denom : denoms) {
        int cantidadNecesaria = restante.divide(denom.getValor(), 0, ROUND_DOWN).intValue();
        int cantidadAUsar = Math.min(cantidadNecesaria, cantidadDisponible);
        // ...
    }

}

_Ventajas:_

- ✅ Optimización de recursos
- ✅ Minimiza cantidad de denominaciones
- ✅ Eficiente computacionalmente

---

## 🏛️ Principios SOLID

### 1. _Single Responsibility Principle (SRP)_ 📌

Cada clase tiene una única responsabilidad.

java
// ✅ CORRECTO: Cada servicio tiene una responsabilidad
public class ProductService implements ProductUseCase {
// Solo maneja operaciones de productos
}

public class CarritoService implements CarritoUseCase {
// Solo maneja operaciones del carrito
}

public class PagoService implements PagoUseCase {
// Solo maneja el procesamiento de pagos
}

### 2. _Open/Closed Principle (OCP)_ 🔓

Abierto para extensión, cerrado para modificación.

java
// Port (interfaz) - cerrada para modificación
public interface ProductoRepository {
List<Producto> listarProductos();
}

// Adapter - abierto para extensión
@Repository
public class ProductoJpaAdapter implements ProductoRepository {
// Implementación con JPA
}

// Se puede crear otro adapter sin modificar el código existente
public class ProductoMongoAdapter implements ProductoRepository {
// Implementación con MongoDB
}

### 3. _Liskov Substitution Principle (LSP)_ 🔄

Los objetos de una clase derivada deben poder sustituir a los de la clase base.

java
// Cualquier implementación de UseCase puede ser sustituida
CarritoUseCase carrito = new CarritoService(repository);
// O
CarritoUseCase carrito = new CarritoInMemoryService();

### 4. _Interface Segregation Principle (ISP)_ ✂️

Interfaces específicas en lugar de interfaces genéricas.

java
// ✅ CORRECTO: Interfaces pequeñas y específicas
public interface ProductUseCase {
List<Producto> listarProductos();
}

public interface CarritoUseCase {
Carrito agregarProducto(Long productoId, Integer cantidad);
Carrito actualizarCantidad(Long productoId, Integer cantidad);
}

public interface PagoUseCase {
ResultadoPago procesarPago(Map<Long, Integer> denominaciones);
}

// ❌ INCORRECTO: Interface gigante
// public interface VendingMachineUseCase {
// List<Producto> listarProductos();
// Carrito agregarProducto(...);
// ResultadoPago procesarPago(...);
// // ... muchos más métodos
// }

### 5. _Dependency Inversion Principle (DIP)_ 🔀

Depender de abstracciones, no de implementaciones concretas.

java
// ✅ CORRECTO: El servicio depende de la abstracción (port)
@Service
public class ProductService implements ProductUseCase {
private final ProductoRepository productoRepository; // Interfaz

    public ProductService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

}

// ❌ INCORRECTO: Depender de implementación concreta
// public class ProductService {
// private final ProductoJpaRepository jpaRepository; // Implementación
// }

---

## 📥 Instalación

### Prerrequisitos

- ☕ Java JDK 21
- 🐘 PostgreSQL 12+
- 📦 Node.js 18+ y npm
- 🔨 Maven 3.9+

### Backend

1. _Clonar el repositorio_
   bash
   git clone <repository-url>
   cd BG/appbg

2. _Configurar la base de datos_

Editar appbg/src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vending_machine
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
server.port=8081

1. _Ejecutar el script SQL_
   bash
   psql -U postgres -d vending_machine -f bd.sql

2. _Ejecutar la aplicación_
   bash

# Opción 1: Maven wrapper

./mvnw spring-boot:run

# Opción 2: Maven instalado

mvn spring-boot:run

# Opción 3: Generar JAR y ejecutar

./mvnw clean package
java -jar target/appbg-0.0.1-SNAPSHOT.jar

### Frontend

1. _Instalar dependencias_
   bash
   cd frontend
   npm install

2. _Ejecutar en desarrollo_
   bash
   npm run dev

3. _Build para producción_
   bash
   npm run build
   npm run preview

---

## 🎮 Uso

### 1. Iniciar Backend

bash
cd appbg
./mvnw spring-boot:run

Backend disponible en: <http://localhost:8081>

### 2. Iniciar Frontend

bash
cd frontend
npm run dev

Frontend disponible en: <http://localhost:5173>

### 3. Flujo de Compra

1. _Seleccionar productos_ en el catálogo
2. _Agregar al carrito_ con cantidades deseadas
3. _Ir al carrito_ para revisar la compra
4. _Proceder al pago_
5. _Insertar denominaciones_ hasta cubrir el total
6. _Procesar pago_ - el sistema calcula y devuelve el cambio automáticamente

---

## 📚 Documentación API

### Base URL

<http://localhost:8081>

### Endpoints

#### 🛍️ Productos

_Listar todos los productos_
http
GET /producto

_Respuesta:_
json
[
{
"id": 1,
"nombre": "Café Americano",
"precio": 2.50,
"cantidadDisponible": 10
}
]

#### 🛒 Carrito

_Obtener carrito actual_
http
GET /carrito

_Agregar producto al carrito_
http
POST /carrito/agregar
Content-Type: application/json

{
"productoId": 1,
"cantidad": 2
}

_Actualizar cantidad_
http
PUT /carrito/actualizar
Content-Type: application/json

{
"productoId": 1,
"cantidad": 3
}

_Remover producto_
http
DELETE /carrito/remover/{productoId}

_Limpiar carrito_
http
DELETE /carrito/limpiar

#### 💰 Pago

_Obtener denominaciones disponibles_
http
GET /pago/denominaciones

_Procesar pago_
http
POST /pago/procesar
Content-Type: application/json

{
"denominacionesInsertadas": {
"1": 2, // 2 monedas de $0.01
"6": 1 // 1 billete de $1.00
}
}

_Respuesta:_
json
{
"totalAPagar": 5.00,
"montoInsertado": 10.00,
"cambio": 5.00,
"cambioDetallado": [
{
"valor": 5.00,
"cantidad": 1,
"tipo": "BILLETE"
}
],
"exitoso": true,
"mensaje": "Pago procesado exitosamente"
}

---

## 📁 Estructura del Proyecto

BG/
├── appbg/ # Backend Spring Boot
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/appbg/appbg/
│ │ │ │ ├── AppbgApplication.java # Main class
│ │ │ │ ├── aplications/ # Application Layer
│ │ │ │ │ └── service/ # Use Case implementations
│ │ │ │ │ ├── ProductService.java
│ │ │ │ │ ├── CarritoService.java
│ │ │ │ │ └── PagoService.java
│ │ │ │ ├── domain/ # Domain Layer (Hexagonal Core)
│ │ │ │ │ ├── model/ # Domain models
│ │ │ │ │ │ ├── Producto.java
│ │ │ │ │ │ ├── Carrito.java
│ │ │ │ │ │ ├── ItemCarrito.java
│ │ │ │ │ │ ├── Denominacion.java
│ │ │ │ │ │ ├── ResultadoPago.java
│ │ │ │ │ │ ├── Moneda.java
│ │ │ │ │ │ └── TipoDenominacion.java
│ │ │ │ │ ├── port/ # Ports (Interfaces)
│ │ │ │ │ │ ├── in/ # Input ports (Use Cases)
│ │ │ │ │ │ │ ├── ProductUseCase.java
│ │ │ │ │ │ │ ├── CarritoUseCase.java
│ │ │ │ │ │ │ └── PagoUseCase.java
│ │ │ │ │ │ └── out/ # Output ports (Repositories)
│ │ │ │ │ │ ├── ProductoRepository.java
│ │ │ │ │ │ └── DenominacionRepository.java
│ │ │ │ │ ├── enum/ # Enumerations
│ │ │ │ │ └── exceptions/ # Domain exceptions
│ │ │ │ └── infrastructure/ # Infrastructure Layer
│ │ │ │ ├── in/ # Input adapters
│ │ │ │ │ ├── controller/ # REST Controllers
│ │ │ │ │ │ ├── ProductoController.java
│ │ │ │ │ │ ├── CarritoController.java
│ │ │ │ │ │ └── PagoController.java
│ │ │ │ │ ├── dto/ # Data Transfer Objects
│ │ │ │ │ │ ├── PagoRequest.java
│ │ │ │ │ │ ├── CarritoResponse.java
│ │ │ │ │ │ └── ...
│ │ │ │ │ └── exception/ # Exception handlers
│ │ │ │ ├── out/ # Output adapters
│ │ │ │ │ ├── adapters/ # Repository implementations
│ │ │ │ │ ├── entity/ # JPA Entities
│ │ │ │ │ │ ├── ProductoEntity.java
│ │ │ │ │ │ └── DenominacionEntity.java
│ │ │ │ │ └── repository/ # JPA Repositories
│ │ │ │ │ ├── ProductoJpaRepository.java
│ │ │ │ │ └── DenominacionJpaRepository.java
│ │ │ │ ├── config/ # Configuraciones
│ │ │ │ │ └── CorsConfig.java
│ │ │ │ └── mapper/ # Mappers
│ │ │ └── resources/
│ │ │ └── application.properties
│ │ └── test/
│ └── pom.xml # Maven dependencies
├── frontend/ # Frontend React + Vite
│ ├── src/
│ │ ├── components/ # Componentes reutilizables
│ │ │ ├── Card.jsx
│ │ │ ├── Payment.jsx
│ │ │ └── Piezas.jsx
│ │ ├── pages/ # Páginas principales
│ │ │ ├── Products.jsx
│ │ │ └── Checkout.jsx
│ │ ├── hooks/ # Custom React Hooks
│ │ │ └── useCarrito.js
│ │ ├── icons/ # Componentes de iconos
│ │ ├── App.jsx # Componente raíz
│ │ └── main.jsx # Entry point
│ ├── package.json
│ └── vite.config.js
├── bd.sql # Script de base de datos
└── README.md # Este archivo

---

## 🎯 Casos de Uso Implementados

### UC-01: Listar Productos

_Actor:_ Usuario  
_Flujo:_

1. Usuario accede a la aplicación
2. Sistema muestra catálogo de productos con stock disponible
3. Usuario visualiza nombre, precio y disponibilidad

### UC-02: Agregar Producto al Carrito

_Actor:_ Usuario  
_Flujo:_

1. Usuario selecciona producto
2. Usuario especifica cantidad
3. Sistema valida stock disponible
4. Sistema agrega al carrito
5. Sistema actualiza totales

_Excepciones:_

- Stock insuficiente → Sistema notifica error

### UC-03: Procesar Pago

_Actor:_ Usuario  
_Flujo:_

1. Usuario revisa carrito
2. Usuario inserta denominaciones
3. Sistema valida monto insertado
4. Sistema calcula cambio con algoritmo greedy
5. Sistema valida disponibilidad de denominaciones para cambio
6. Sistema actualiza inventarios (productos y efectivo)
7. Sistema muestra cambio detallado
8. Sistema limpia carrito

_Excepciones:_

- Monto insuficiente → Solicitar más dinero
- Sin cambio disponible → Rechazar transacción

---

## 🧪 Testing

### Ejecutar Tests Backend

bash
cd appbg
./mvnw test

### Ejecutar Tests Frontend

bash
cd frontend
npm run test

---

## 🔒 Seguridad

- ✅ Validación de datos de entrada con Spring Validation
- ✅ CORS configurado para desarrollo
- ✅ Transaccionalidad en operaciones críticas
- ✅ Validación de stock antes de confirmación
- ✅ Validación de cambio antes de procesar pago

---

## 🚀 Roadmap Futuro

- [ ] Autenticación y autorización con JWT
- [ ] Panel de administración
- [ ] Reportes de ventas
- [ ] Sistema de notificaciones
- [ ] Integración con pasarelas de pago
- [ ] App móvil con React Native
- [ ] Soporte multi-moneda
- [ ] Analytics en tiempo real
- [ ] Sistema de descuentos y promociones

---

## 👥 Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama feature (git checkout -b feature/AmazingFeature)
3. Commit tus cambios (git commit -m 'Add: amazing feature')
4. Push a la rama (git push origin feature/AmazingFeature)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.

---

## 📞 Contacto

_Proyecto Verde - Reto Grupo SL 2026_

⭐ Si este proyecto te fue útil, no olvides darle una estrella en GitHub!

---

<div align="center">

_[⬆ Volver arriba](#-máquina-expendedora-inteligente)_

Hecho con ❤️ por el equipo de desarrollo

</div>
