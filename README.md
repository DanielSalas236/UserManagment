# 👥 UserManagment - Sistema de Gestión de Usuarios

Una aplicación Spring Boot para la gestión de usuarios con API REST, validaciones de negocio y tests unitarios e integración.

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalados:

- **Java 11+**: [Descargar Java](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** (opcional): La aplicación incluye Maven Wrapper
- **Git** (opcional): Para clonar el repositorio

Verifica la instalación con:
```bash
java -version
```

## 🏗️ Estructura del Proyecto

```
UserManagment/
├── src/
│   ├── main/java/com/ditech/usermanagment/
│   │   ├── controller/          # Endpoints REST
│   │   ├── service/             # Lógica de negocio
│   │   ├── model/               # Entidades
│   │   ├── repository/          # Acceso a datos
│   │   ├── exception/           # Excepciones personalizadas
│   │   └── dto/                 # Objetos de transferencia
│   ├── test/java/               # Tests unitarios e integración
│   └── resources/               # Configuración y recursos
├── pom.xml                      # Dependencias Maven
├── mvnw / mvnw.cmd             # Maven Wrapper (Windows/Linux)
└── README.md                    # Esta documentación
```

## 🚀 Cómo Ejecutar la Aplicación

### Opción 1: Usando Maven Wrapper (Recomendado - Sin instalar Maven)

**En Windows/Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Opción 2: Usando Maven Instalado

```bash
mvn spring-boot:run
```

### Verificar que la aplicación está ejecutándose

Una vez iniciada, deberías ver en la consola:
```
UserManagmentApplication : Started UserManagmentApplication in X.XXX seconds
```

La aplicación estará disponible en: `http://localhost:8081`

## 📚 Documentación de la API (Swagger)

Accede a la documentación interactiva de la API:

**URL:** `http://localhost:8081/swagger-ui/index.html`

### Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/users` | Obtiene lista de todos los usuarios |
| **GET** | `/users/{id}` | Obtiene un usuario específico por ID |
| **POST** | `/users` | Crea un nuevo usuario |
| **DELETE** | `/users/{id}` | Elimina un usuario |

### Ejemplo de uso con cURL

**Obtener todos los usuarios:**
```bash
curl -X GET http://localhost:8081/users
```

**Crear un nuevo usuario:**
```bash
curl -X POST http://localhost:8081/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "active": true
  }'
```

**Obtener un usuario específico:**
```bash
curl -X GET http://localhost:8081/users/1
```

**Eliminar un usuario:**
```bash
curl -X DELETE http://localhost:8081/users/1
```

## 🧪 Cómo Ejecutar los Tests

### Ejecutar todos los tests

**Con Maven Wrapper (Windows/Linux/Mac):**
```bash
./mvnw clean test
# O en Windows también
mvnw.cmd clean test
```

**Con Maven instalado:**
```bash
mvn clean test
```

### Ejecutar tests específicos

**Solo tests del controlador:**
```bash
./mvnw clean test -DtestNamePattern=UserControllerTest
```

**Solo tests del servicio:**
```bash
./mvnw clean test -DtestNamePattern=UserServiceTest
```

### Resultado esperado

```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Cobertura de Tests

La aplicación incluye los siguientes tests:

| Clase | Método | Descripción |
|-------|--------|-------------|
| **UserControllerTest** | testGetAllUsers() | Verifica GET /users retorna HTTP 200 con lista |
| **UserControllerTest** | testCreateUser() | Verifica POST /users crea usuario correctamente |
| **UserServiceTest** | testSaveUser() | Verifica que se guarda un usuario con ID asignado |
| **UserServiceTest** | testGetUserByIdThrowsException() | Verifica que lanza excepción si usuario no existe |
| **UserManagmentApplicationTests** | contextLoads() | Verifica que el contexto carga correctamente |

## 🛑 Cómo Detener la Aplicación

### Si se ejecuta en la terminal

Presiona `Ctrl + C` en la terminal donde está ejecutándose la aplicación.

```bash
# En Windows
Presiona: Ctrl + C

# En Linux/Mac
Presiona: Ctrl + C
```

Se mostrará un mensaje de confirmación:
```
Shutting down gracefully...
HikariPool-1 - Shutdown completed
```

### Si se ejecuta en un IDE (IntelliJ IDEA, Eclipse, etc.)

- Haz clic en el botón **Stop** (⏹️) en la ventana de ejecución
- O presiona `Ctrl + F2` en IntelliJ IDEA

### Compilar el JAR ejecutable

Para compilar la aplicación como un JAR ejecutable:

```bash
./mvnw clean package
# O en Windows también
mvnw.cmd clean package
```

Esto genera `target/UserManagment-0.0.1-SNAPSHOT.jar`

Ejecutar el JAR:
```bash
java -jar target/UserManagment-0.0.1-SNAPSHOT.jar
```

Detener el JAR:
```bash
Ctrl + C
```

## 🔧 Configuración

### Archivo `application.properties`

La aplicación usa una base de datos H2 en memoria por defecto. Para modificar la configuración:

```properties
# Puerto de la aplicación
server.port=8081

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
```

Consola H2: `http://localhost:8081/h2-console`

## 📦 Dependencias Principales

- **Spring Boot 2.7.3**: Framework web
- **Spring Data JPA**: Acceso a datos
- **H2 Database**: Base de datos en memoria
- **Lombok**: Reducción de código repetitivo
- **Mockito 4.11.0**: Testing con mocks
- **Jackson**: Serialización JSON
- **Springdoc OpenAPI**: Documentación API

## 🐛 Resolución de Problemas

### Puerto 8080 en uso

Si reciben error de puerto en uso:
```bash
# Windows: Cambiar puerto en application.properties
server.port=8081

# O matar el proceso
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Problemas de compilación

```bash
# Limpiar cache de Maven
mvnw.cmd clean
mvnw.cmd compile
```

### Tests fallando

```bash
# Ejecutar con salida completa
mvnw.cmd clean test -X
```

## 📄 Archivos de Configuración

- `pom.xml`: Dependencias y configuración Maven
- `application.properties`: Propiedades de la aplicación
- `.gitignore`: Archivos ignorados por Git

