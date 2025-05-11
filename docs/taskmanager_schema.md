
## 📦 Esquema de Base de Datos: `taskmanager`

### 🗃️ Tabla: `users`

| Campo       | Tipo                | Restricciones           | Descripción                      |
|-------------|---------------------|--------------------------|----------------------------------|
| id          | varchar(255)        | PRIMARY KEY              | Identificador único              |
| created_at  | datetime(6)         | NOT NULL                 | Fecha de creación del usuario    |
| email       | varchar(255)        | NOT NULL, UNIQUE         | Correo electrónico del usuario   |
| name        | varchar(100)        | NOT NULL                 | Nombre del usuario               |
| password    | varchar(255)        | NOT NULL                 | Contraseña cifrada               |
| role        | enum('ADMIN','USER')| NOT NULL                 | Rol del usuario                  |
| updated_at  | datetime(6)         | NOT NULL                 | Fecha de última actualización    |

---

### 📋 Tabla: `tasks`

| Campo        | Tipo                                       | Restricciones           | Descripción                           |
|--------------|--------------------------------------------|--------------------------|---------------------------------------|
| id           | varchar(255)                               | PRIMARY KEY              | Identificador único de la tarea       |
| created_at   | datetime(6)                                | NOT NULL                 | Fecha de creación de la tarea         |
| description  | text                                       |                          | Descripción de la tarea               |
| due_date     | date                                       | NOT NULL                 | Fecha de vencimiento                  |
| priority     | enum('HIGH','LOW','MEDIUM')               | NOT NULL                 | Prioridad de la tarea                 |
| status       | enum('COMPLETED','IN_PROGRESS','PENDING') | NOT NULL                 | Estado de la tarea                    |
| title        | varchar(255)                               | NOT NULL                 | Título de la tarea                    |
| updated_at   | datetime(6)                                | NOT NULL                 | Fecha de última actualización         |
| user_id      | varchar(255)                               | NOT NULL, FOREIGN KEY    | Relación con `users(id)`             |

---

### 🔗 Relaciones

- `tasks.user_id` → `users.id`

Cada tarea pertenece a un único usuario.

