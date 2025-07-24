# ForoHub 🧠💬

**ForoHub** es una aplicación web de foros de discusión construida con Java y Spring Boot. Permite a los usuarios registrarse, autenticarse y participar activamente creando y comentando temas en distintos cursos.

---

## 🚀 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring Security** (con autenticación JWT)
- **JPA/Hibernate** (ORM)
- **MySQL** como base de datos
- **Flyway** para migraciones automáticas
- **Lombok** para simplificar el código Java
- **Maven** como sistema de construcción
- **Postman** (para pruebas de la API)

---

## 🔐 Autenticación

El sistema utiliza **JWT (JSON Web Tokens)** para manejar la autenticación.

### Flujo de login:
1. El usuario envía correo y contraseña.
2. El backend genera un token válido por 2 horas.
3. El usuario debe incluir el token en el header de sus solicitudes:


---

## 📂 Estructura del Proyecto

src/
├── main/
│ ├── java/com/gelse/forohub/
│ │ ├── controller/ --> Controladores REST
│ │ ├── domain/ --> Entidades del dominio (Usuario, Topico, Curso)
│ │ ├── infra/security/ --> Seguridad: Filtros, TokenService
│ │ ├── repository/ --> Repositorios JPA
│ │ └── ForohubApplication.java
│ └── resources/
│ ├── application.properties
│ └── db/migration/ --> Scripts SQL para Flyway (V1__init.sql, etc.)

---

## 🗃️ Scripts Flyway (Migraciones)

Los scripts SQL están ubicados en:

src/main/resources/db/migration/

Y tienen nombres como:  

V1__init.sql
V2__add_estado_topico.sql


Al iniciar la aplicación, Flyway ejecutará las migraciones automáticamente si hay cambios.

---

## 🛠 Endpoints principales

| Método | Endpoint            | Descripción                     |
|--------|---------------------|---------------------------------|
| POST   | `/login`            | Autenticación y obtención token |
| POST   | `/usuarios`         | Registro de nuevo usuario       |
| GET    | `/topicos`          | Lista de todos los tópicos      |
| POST   | `/topicos`          | Crear nuevo tópico              |
| PUT    | `/topicos/{id}`     | Actualizar tópico               |
| DELETE | `/topicos/{id}`     | Eliminar tópico                 |

> ⚠️ Los endpoints protegidos requieren el token JWT en el encabezado Authorization.

---

## 🧪 Cómo correr el proyecto localmente

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/forohub.git
   cd forohub
Crea una base de datos MySQL:

CREATE DATABASE forohub;

Configura tu archivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
api.security.token.secret=clave-secreta-super-segura
spring.jpa.hibernate.ddl-auto=none
Corre el proyecto desde tu IDE o terminal:

./mvnw spring-boot:run

✅ TODOs futuros
 Interfaz web con React o Thymeleaf

 Moderación de tópicos

 Comentarios anidados

 Notificaciones por correo

👨‍💻 Autor
Desarrollado por Gelse con ❤️ y Java.

📝 Licencia
MIT License. Puedes usar este proyecto libremente para fines educativos o comerciales.

---

✅ Si quieres, también puedo ayudarte a integrarlo directamente en tu repositorio o generarlo como archivo para descargar.
