# Link-in-Bio Application

The _Link-in-Bio_ application is a link management platform (similar to Linktree) built with Java Spring Boot. It allows users to create a profile containing a list of important links to share on social media.

## 🚀 Key Features

- **Profile Management**: Users can manage their profile details (name, bio, avatar).
- **Link Management**: Add, edit, and delete links for their profiles.
- **Secure Authentication**: Login and registration system using Spring Security with JWT (JSON Web Token).
- **Stateless API**: Uses an efficient RESTful architecture.

## 🛠️ Technologies Used

- **Backend**: Java 17+, Spring Boot 3.x
- **Security**: Spring Security (JWT, BCrypt)
- **Database**: [Mention your database, e.g., MySQL/PostgreSQL]
- **Build Tool**: Maven
- **Lombok**: To reduce boilerplate code

## 📋 Prerequisites

Before running the application, ensure you have installed:

- Java JDK 17 or newer.
- Maven.
- [Database used] (ensure the database is running).

## ⚙️ How to Run

1. **Clone the repository**:

   ```bash
   git clone [https://github.com/marcel-maruli/link-in-bio.git](https://github.com/marcel-maruli/link-in-bio.git)
   cd link-in-bio
   ```

2. **Database Configuration:**

Open src/main/resources/application.properties (or application.yml) and adjust your database connection:

```Properties
spring.application.name
spring.datasource.url=jdbc:postgresql://localhost:5432/link_in_bio_db
spring.datasource.username=marcel
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
app.jwt.secret=${JWT_SECRET:ThisIsSecret}
```

3. **Run the application:**
   Make Sure that your database is ready and active, and you already install mvn globally in your local computer, then run the application by using this command:

```bash
mvn spring-boot:run
```

The application will run at http://localhost:8080

## 📖 Project Structure

```bash
controller/: Handles API requests (Auth, Link, Profile).
dto/: Data Transfer Objects for data exchange.
model/: Database entities (User, Profile, Link).
repository/: Database access interfaces.
service/: Business logic and data processing.
config/: Application configuration (including SecurityConfig).
```

# 📖 API Documentation

## Auth

### Register

**Endpoint**

```http
POST /api/auth/register
```

**Request Body**

```json
{
  "username": "red_panda",
  "fullName": "Wanda Witch",
  "password": "12345"
}
```

**Response — 201 Created**

```json
{
  "data": {
    "fullName": "Wanda Witch",
    "id": 12,
    "username": "red_panda"
  },
  "success": true
}
```

### Login

**Endpoint**

```http
POST /api/auth/login
```

**Request Body**

```json
{
  "username": "red_panda",
  "password": "12345"
}
```

**Response — 200 Success**

```json
{
    "token": <HASHED_TOKEN>,
    "username": "red_panda",
    "type": "Bearer"
}
```

**Request Body**

```json
{
  "username": "red_panda",
  "password": "12345"
}
```

**Response — 200 Success**

```json
{
    "token": <HASHED_TOKEN>,
    "username": "red_panda",
    "type": "Bearer"
}
```

### Get Profile

**Endpoint**

```http
GET /api/public/<username>
```

**Response — 200 Success**

```json
{
  "avatarUrl": null,
  "bio": "",
  "displayName": "Wanda Witch",
  "id": "profile_011",
  "links": null,
  "user": {
    "fullName": null,
    "username": "red_panda"
  }
}
```

**Request Body**

```json
{
  "username": "red_panda",
  "password": "12345"
}
```

**Response — 200 Success**

```json
{
  "avatarUrl": null,
  "bio": "",
  "displayName": "Wanda Witch",
  "id": "profile_011",
  "links": null,
  "user": {
    "fullName": "Wanda Witch",
    "username": "red_panda"
  }
}
```

**Response — 400 Bad Request**

```json
{
  "success": false,
  "error": "Profile not found"
}
```

### Public Routes

| Method     | Endpoint                    | Deskripsi                        |
| :--------- | :-------------------------- | :------------------------------- |
| `POST`     | `/api/auth/login`           | Login user untuk mendapatkan JWT |
| `POST`     | `/api/auth/register`        | Registrasi user baru             |
| `GET`      | `/api/links/`               | Mendapatkan semua link publik    |
| `GET`      | `/api/links/{linkId}`       | Mendapatkan detail link spesifik |
| `POST/GET` | `/api/public/**`            | Endpoint untuk konten publik     |
| `GET`      | `/api/analytics`            | Mendapatkan data analitik publik |
| `GET`      | `/api/links/{linkId}/click` | Melacak klik pada link           |

### Private Routes

Endpoints not listed in the table above are **protected**. If you do not provide a token, or if the provided token is invalid, the server will deny access and return a 401 Unauthorized response.

**1. Authentication Scheme**
The application utilizes the Bearer Token scheme. Every request to a protected endpoint must include the Authorization header in the following format:

```HTTP
Authorization: Bearer <TOKEN_THAT_YOU'VE_GOT_WHEN_LOGIN>
```

**2. Error Handling**
If authentication fails, the API returns a JSON response in the following format:

Format Error

```json
{
  "error": "Unauthorized",
  "message": "Token is not valid!"
}
```

**3. HTTP Status Codes**

| Status Code                 | Description                                |
| :-------------------------- | :----------------------------------------- |
| `401 Unauthorized`          | Token missing, expired, or invalid.        |
| `500 Internal Server Error` | Server-side error during token validation. |
