# Sistema de Gestión de Inventario - Refaccionaria Camalu

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## Tabla de Contenidos
- [Requisitos Técnicos](#requisitos-técnicos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Instalación](#instalación)
- [Uso](#acceso-al-sistema)
- [Documentación](#documentación)

## Requisitos Técnicos

### Versiones
- **Java**: 21
- **Spring Boot**: 3.2.0
- **Maven**: 3.6.3 o superior
- **Base de datos**: H2 (embebida)

### Dependencias Principales
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Validation
- H2 Database
- Lombok
- Spring Boot DevTools

## Configuración del Entorno

1. **Requisitos previos**
   - Java 21 JDK instalado
   - Maven 3.6.3 o superior
   - Opcional: Un IDE como IntelliJ IDEA o VS Code

2. **Variables de entorno**
   - `SPRING_PROFILES_ACTIVE`: Perfil de Spring a utilizar (dev, prod)
   - `SERVER_PORT`: Puerto en el que se ejecutará la aplicación (por defecto: 8080)

## Instalación

1. **Clonar el repositorio**
   ```bash
   git clone [https://github.com/eliantte00/inventory.manager]
   cd inventory.manager
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   Abre tu navegador y navega a:
   ```
   http://localhost:8080
   ```

## Documentación

### Estructura del Proyecto
```
src/
├── main/
│   ├── java/reftools/inventory/manager/
│   │   ├── config/        # Configuraciones de Spring
│   │   ├── controller/    # Controladores MVC y REST
│   │   ├── model/         # Entidades JPA
│   │   ├── repository/    # Repositorios de datos
│   │   ├── service/       # Lógica de negocio
│   │   └── Application.java
│   └── resources/
│       ├── static/        # Archivos estáticos (CSS, JS)
│       ├── templates/     # Plantillas Thymeleaf
│       └── application.properties
└── test/                  # Pruebas unitarias y de integracion
```

### Base de Datos
La aplicación utiliza una base de datos H2 embebida por defecto, que se inicializa automáticamente al arrancar la aplicación.

- **Consola H2**: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:file:./data/inventorydb`
  - Usuario: `sa`
  - Contraseña: (dejar en blanco)

### Perfiles
- **dev**: Perfil de desarrollo con H2 embebida y datos de prueba
- **prod**: Perfil de producción (configurar base de datos externa en `application-prod.properties`)

## Licencia
Este proyecto está bajo la licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

# Sistema de Gestión de Inventario - Refaccionaria Camalu

## Descripción General
El Sistema de Gestión de Inventario de Refacciones es una aplicación web diseñada para administrar el inventario de refacciones automotrices. Permite registrar, consultar, actualizar y dar seguimiento al movimiento de piezas en el inventario.

## Requisitos del Sistema
- Navegador web moderno (Chrome, Firefox, Edge, Safari)
- Conexión a Internet (si la aplicación está desplegada en un servidor)

## Acceso al Sistema
1. Abre tu navegador web preferido
2. Ingresa la URL de la aplicación en la barra de direcciones

## Funcionalidades Principales

### 1. Gestión de Refacciones

#### Agregar una Nueva Refacción
1. Haz clic en la pestaña "Agregar refacción"
2. Completa el formulario con los siguientes datos:
   - Nombre de la refacción
   - Marca
   - SKU (código único)
   - Precio
   - Cantidad en existencia
3. Haz clic en "Guardar refacción"

#### Editar una Refacción Existente
1. En la pestaña "Consultar inventario", localiza la refacción
2. Haz clic en "Editar" junto a la refacción que deseas modificar
3. Realiza los cambios necesarios
4. Haz clic en "Guardar cambios" o "Cancelar" para descartar

#### Eliminar una Refacción
1. En la pestaña "Consultar inventario", localiza la refacción
2. Haz clic en "Eliminar" junto a la refacción que deseas eliminar
3. Confirma la eliminación en el mensaje emergente

### 2. Gestión de Inventario

#### Registrar Movimientos de Inventario
1. En la pestaña "Consultar inventario", localiza la refacción
2. Haz clic en "Movimiento"
3. En el modal que aparece:
   - Selecciona el tipo de movimiento (Entrada o Salida)
   - Ingresa la cantidad
   - Opcionalmente, ingresa una razón para el movimiento
4. Haz clic en "Guardar movimiento"

#### Consultar Historial de Movimientos
1. En la pestaña "Consultar inventario", localiza la refacción
2. Haz clic en "Historial" para ver todos los movimientos registrados

### 3. Búsqueda y Filtrado

#### Buscar Refacciones
1. En la pestaña "Consultar inventario"
2. Utiliza el campo de búsqueda para buscar por:
   - Nombre
   - Marca
   - SKU
3. Presiona "Buscar" o presiona Enter

#### Ordenar por Existencias
1. En la pestaña "Consultar inventario"
2. Haz clic en "Ordenar por existencias" para ordenar las refacciones por cantidad disponible

## Mensajes del Sistema
- **Verde**: Operación exitosa (ej: "La refacción se registró correctamente")
- **Rojo**: Error en la operación (ej: "Ya existe una refacción con ese SKU")

## Consejos de Uso
- Utiliza códigos SKU únicos para cada refacción
- Mantén actualizadas las cantidades en inventario
- Registra todos los movimientos de inventario para mantener un historial preciso
- Utiliza el campo de razón en los movimientos para mantener un registro claro de cada transacción

## Solución de Problemas
- Si no encuentras una refacción, verifica que estés buscando con el término correcto
- Si recibes un mensaje de error al guardar, verifica que todos los campos obligatorios estén completos
- Para cantidades negativas, asegúrate de que existan suficientes existencias antes de registrar una salida
nica o reporte de problemas, por favor contacta al administrador del sistema.