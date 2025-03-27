# Task Manager API

Este proyecto es una API para la gestión de tareas, desarrollada con Spring Boot y siguiendo una arquitectura modular con principios SOLID y Clean Code.

## Tecnologías utilizadas
- **Spring Boot** (Framework principal)
- **Spring Security** con JWT (Autenticación y autorización)
- **MySQL** (Base de datos relacional)
- **JPA/Hibernate** (ORM para persistencia de datos)
- **Swagger/OpenAPI** (Documentación de la API)
- **JUnit & Mockito** (Para pruebas unitarias y de integración, próximamente)
- **GitHub Actions** (CI/CD para despliegue automatizado)

## Estructura del proyecto

La estructura del proyecto sigue una arquitectura en capas, dividida por módulos:
```
📂 src/main/java/com/gustavogiraldo/taskmanager
├── 📂 authentication (Manejo de autenticación y seguridad)
│   ├── controller/
│   ├── dto/
│   ├── security/
│   ├── service/
│
├── 📂 config (Configuraciones generales del proyecto)
│   ├── GlobalExceptionHandler.java
│   ├── OpenAPIConfig.java
│
├── 📂 task (Módulo de gestión de tareas)
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── repository/
│   ├── service/
│
├── 📂 user (Módulo de gestión de usuarios)
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── repository/
│   ├── service/
│
└── TaskmanagerApplication.java (Punto de entrada de la aplicación)
```

## Configuración y ejecución del proyecto

### 1. Clonar el repositorio
```sh
git clone https://github.com/tu-usuario/taskmanager.git
cd taskmanager
```

### 2. Configurar la base de datos
En el archivo `application.properties` o `application.yml`, asegúrate de configurar correctamente la conexión a MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecutar la aplicación
Puedes ejecutar la aplicación con Maven o desde tu IDE (IntelliJ IDEA, Eclipse, etc.).
```sh
mvn spring-boot:run
```

### 4. Acceder a la API
Una vez la aplicación esté en ejecución, puedes acceder a:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **API Base:** `http://localhost:8080/api`

## Decisiones técnicas y diseño
- **Arquitectura modular:** Separación clara entre autenticación, tareas y usuarios.
- **Seguridad con JWT:** Los endpoints están protegidos y requieren autenticación con tokens JWT.
- **Separación de responsabilidades:** Uso de servicios para lógica de negocio y controladores para manejar peticiones.
- **Pruebas unitarias:** Se implementarán próximamente con JUnit y Mockito para garantizar calidad del código.

## Pruebas y cobertura
*(Sección en construcción, se actualizará una vez se implementen las pruebas unitarias.)*

---

Este README se actualizará conforme avance el desarrollo del proyecto. 🚀

