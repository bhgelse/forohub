
# ForoHub 🧠💬

**ForoHub** es una aplicación web construida con **Java + Spring Boot** que permite a los usuarios participar en un foro de discusión académico. Puedes crear cuentas, iniciar sesión, publicar tópicos, responder y consultar publicaciones en cursos específicos.

> 🔗 Repositorio del proyecto: [github.com/bhgelse/forohub](https://github.com/bhgelse/forohub)

---

## 🚀 Tecnologías principales

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **JPA / Hibernate**
- **MySQL**
- **Flyway (para migraciones)**
- **Lombok**
- **Maven**

---

## 📂 Estructura del Proyecto

```

forohub/
├── controller/         # Controladores REST
├── domain/             # Entidades del modelo (Topico, Usuario, Curso)
├── infra/security/     # Seguridad JWT y filtros
├── repository/         # Interfaces JpaRepository
├── resources/
│   ├── application.properties
│   └── db/migration/   # Scripts SQL para Flyway
└── ForohubApplication.java

```

---

## 🔐 Autenticación con JWT

El backend utiliza JWT para proteger rutas privadas.  
Para acceder a endpoints protegidos:

1. Autentícate con `/login` (envía correo y contraseña)
2. El backend devuelve un token JWT.
3. Usa este token en tus peticiones:

```

Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...

````

---

## 🔧 Configuración local

1. Clona el repositorio:

   ```bash
   git clone https://github.com/bhgelse/forohub.git
   cd forohub
````

2. Crea la base de datos en MySQL:

   ```sql
   CREATE DATABASE forohub;
   ```

3. Configura `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA

   api.security.token.secret=clave-secreta-segura
   spring.jpa.hibernate.ddl-auto=none
   ```

4. Ejecuta el proyecto:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📌 Endpoints principales

| Método | Endpoint        | Descripción                    |
| ------ | --------------- | ------------------------------ |
| POST   | `/login`        | Iniciar sesión y obtener token |
| POST   | `/usuarios`     | Crear un nuevo usuario         |
| GET    | `/topicos`      | Listar todos los tópicos       |
| POST   | `/topicos`      | Crear un nuevo tópico          |
| PUT    | `/topicos/{id}` | Editar tópico                  |
| DELETE | `/topicos/{id}` | Eliminar tópico                |

> ⚠️ Algunos requieren autenticación vía JWT.

---

## 🧪 Migraciones Flyway

Las migraciones SQL están en:

```
src/main/resources/db/migration/
```

Ejemplo:

```sql
V1__init.sql      -- Crea tablas principales
V2__estado.sql    -- Agrega campo 'estado' a topicos
```

Se ejecutan automáticamente al iniciar la app.

---

## 💡 TODOs

* [ ] UI web (React, Angular o Thymeleaf)
* [ ] Comentarios a tópicos
* [ ] Sistema de notificaciones
* [ ] Paginación avanzada
* [ ] Moderación de contenido

---

## 👤 Autor

Desarrollado por [Gelse](https://github.com/bhgelse) con ❤️ desde Colombia.
Si te fue útil, ¡dale ⭐ al repo!

---

## 📄 Licencia

Este proyecto está licenciado bajo MIT.
Puedes usarlo, modificarlo y distribuirlo libremente.

```

---

¿Quieres también que te genere automáticamente este archivo y lo agregues a tu proyecto en GitHub con un `git commit`? Puedo guiarte paso a paso.
```
