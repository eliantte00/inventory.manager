# Sistema de Gestión de Inventarios – Fase IV

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## Resumen Ejecutivo

### Descripción del Proyecto
El Sistema de Gestión de Inventarios para Refaccionaria Camalu es una solución integral desarrollada para optimizar el control y seguimiento de inventario de refacciones automotrices. Esta aplicación web permite gestionar de manera eficiente el registro, consulta, actualización y seguimiento de movimientos de inventario.

### Problema Identificado
Las refaccionarias enfrentan desafíos significativos en la gestión de inventario, incluyendo:
- Pérdida de tiempo en procesos manuales de registro
- Errores frecuentes en el conteo de existencias
- Dificultad para rastrear movimientos de inventario
- Falta de visibilidad en tiempo real de los niveles de stock
- Ineficiencias en la gestión de proveedores y órdenes de compra

### Solución Propuesta
El sistema implementa una solución tecnológica que:
- Digitaliza el registro de refacciones y movimientos
- Proporciona seguimiento en tiempo real del inventario
- Facilita la generación de reportes y análisis
- Mejora la precisión en el control de existencias
- Optimiza los procesos de reposición de inventario

### Arquitectura General
| Capa | Tecnologías                                     |
|------|-------------------------------------------------|
| **Frontend** | Thymeleaf, HTML5, CSS3, JavaScript, Bootstrap 5 |
| **Backend** | Java 21, Spring Boot 3.2.0, Spring Data JPA     |
| **Base de Datos** | H2 (desarrollo), MySQL (producción)              |
| **Autenticación** | Spring Security                                 |
| **CI/CD** | GitHub Actions                                  |

## Tabla de Contenidos
- [Requerimientos](#requerimientos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Contribución](#contribución)
- [Roadmap](#roadmap)
- [Demo en Video](#demo-en-video)

## Requerimientos

### Servidores
- **Desarrollo**: Local (mínimo 4GB RAM, 2 núcleos)
- **Producción**: Servidor en la nube (Heroku, AWS, GCP, etc.)

### Software
| Componente | Versión |
|------------|---------|
| Java JDK | 21 |
| Maven | 3.6.3+ |
| Git | 2.25.0+ |

### Base de Datos
- **Desarrollo**: H2 Database Engine (embebida)
- **Producción**: MySQL

### Dependencias Principales
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## Instalación

### 1. Clonar el Repositorio
```bash
git clone https://github.com/eliantte00/inventory.manager.git
cd inventory.manager
```

### 2. Configuración del Entorno
1. Instalar Java 21 JDK
2. Instalar Maven 3.6.3 o superior
3. Configurar variables de entorno:
   - `JAVA_HOME`: Ruta al JDK 21
   - Añadir Maven al PATH

### 3. Instalar Dependencias
```bash
mvn clean install
```

### 4. Configuración de Base de Datos
#### Desarrollo (H2)
La base de datos H2 se configura automáticamente. Accede a la consola en:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/inventorydb`
- Usuario: `sa`
- Contraseña: (dejar en blanco)

#### Producción (MySQL)
1. Crear base de datos en MySQL
2. Configurar `application-prod.properties` con credenciales

### 5. Ejecutar la Aplicación
```bash
# Modo desarrollo (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Modo producción (PostgreSQL)
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### 6. Ejecutar Pruebas
```bash
mvn test
```

### 7. Despliegue en Producción
#### Heroku
1. Instalar Heroku CLI
2. Login: `heroku login`
3. Crear app: `heroku create`
4. Desplegar: `git push heroku main`

#### Docker
```bash
docker build -t inventory-manager .
docker run -p 8080:8080 inventory-manager
```

## Configuración

### Archivo application.properties
```properties
# Configuración del servidor
server.port=8080
server.servlet.context-path=/

# Configuración de H2 (desarrollo)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:file:./data/inventorydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# Configuración de Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Configuración de logging
logging.level.root=INFO
logging.level.com.refaccionaria=DEBUG
```

### Variables de Entorno para Producción
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/inventorydb
export SPRING_DATASOURCE_USERNAME=user
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

## Uso

### Manual para Usuario Final
1. **Inicio**
   - Acceder a la URL de la aplicación (https://localhost:8080)

2. **Gestión de Refacciones**
   - Agregar nuevas refacciones
   - Editar existentes
   - Eliminar refacciones obsoletas
   - Consultar inventario

3. **Movimientos de Inventario**
   - Registrar entradas
   - Registrar salidas
   - Consultar historial

## Contribución

### Guía de Contribución
1. **Clonar el Repositorio**
   ```bash
   git clone https://github.com/eliantte00/inventory.manager.git
   cd inventory.manager
   ```

2. **Crear una Rama**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

3. **Realizar Cambios**
   - Sigue las convenciones de código
   - Añade pruebas unitarias
   - Actualiza la documentación

4. **Hacer Commit**
   ```bash
   git add .
   git commit -m "Descripción clara de los cambios"
   ```

5. **Hacer Push**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```

6. **Crear Pull Request**
   - Ir a GitHub
   - Crear Pull Request desde tu rama
   - Esperar revisión y aprobación

## Roadmap

### Próximas Características
- [ ] Autenticación con JWT
- [ ] Integración con API de proveedores
- [ ] Aplicación móvil (Android/iOS)
- [ ] Dashboard con métricas en tiempo real
- [ ] Sistema de notificaciones
- [ ] Integración con sistemas de punto de venta
- [ ] Análisis predictivo de inventario
- [ ] Soporte multi-almacén

### Mejoras Planeadas
- Optimización de consultas
- Mejoras en la interfaz de usuario
- Mejor manejo de errores
- Pruebas de carga y rendimiento
- Internacionalización (i18n)

## Demo en Video

[Enlace al video demostrativo](https://youtu.be/lcMVMG6rOTQ)

### Despliegue Local
1. Descargar el archivo JAR desde [Releases](https://github.com/eliantte00/inventory.manager/releases)
2. Ejecutar: `java -jar inventory-manager-1.0.0.jar`
3. Abrir navegador en: http://localhost:8080

### Institución
Universidad TecMilenio

### Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---
