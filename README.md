# Employee Management System

A secure, full-featured REST API built with Spring Boot for managing employees and departments, featuring JWT-based authentication and role-based access control.

## Features

- **JWT Authentication** — Stateless login with token-based session management
- **Password Security** — BCrypt password hashing (no plain-text storage)
- **Employee CRUD** — Create, read, update, and delete employee records
- **Department Management** — Organize employees into departments with a One-to-Many relationship
- **Role-Based Access** — Admin and Employee roles with Spring Security
- **Input Validation** — Request validation using Jakarta Bean Validation (`@NotBlank`, `@Email`)
- **Layered Architecture** — Clean separation of Controller, Service, and Repository layers

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.1.1 |
| Security | Spring Security, JWT (jjwt) |
| Database | MySQL, Spring Data JPA (Hibernate) |
| Build Tool | Maven |
| Utilities | Lombok |

## API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/auth/login` | Login and receive JWT token | No |

### Employees
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/employees` | Register a new employee | No |
| GET | `/api/employees` | Get all employees | Yes |
| GET | `/api/employees/{id}` | Get employee by ID | Yes |
| PUT | `/api/employees/{id}` | Update employee details | Yes |
| DELETE | `/api/employees/{id}` | Delete an employee | Yes |

### Departments
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/departments` | Create a new department | Yes |
| GET | `/api/departments` | Get all departments | Yes |

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL 8+

### Setup

1. Clone the repository
```bash
git clone https://github.com/Jayanandmanik/employee-management-system.git
cd employee-management-system
```

2. Create a MySQL database
```sql
CREATE DATABASE employee_db;
```

3. Copy `application.properties.example` to `application.properties` and fill in your credentials
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

4. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

### Sample Request — Register an Employee

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepass123",
    "salary": 50000,
    "role": "EMPLOYEE",
    "department": { "id": 1 }
  }'
```

### Sample Request — Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securepass123"
  }'
```

Response includes a JWT token to be used in the `Authorization: Bearer <token>` header for subsequent requests.

## Project Structure

```
src/main/java/com/manik/employee_management/
├── controller/    → REST API endpoints
├── service/       → Business logic
├── repository/    → Data access layer (Spring Data JPA)
├── entity/        → JPA entities (Employee, Department)
├── security/      → JWT utilities, filters, and security config
└── dto/           → Request/response data transfer objects
```

## Author

**Jayanand Manik Maddirala**
B.Tech Computer Science | [GitHub](https://github.com/Jayanandmanik)