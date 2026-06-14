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

3. **Run the application:**:

Open src/main/resources/application.properties (or application.yml) and adjust your database connection:

```bash
./mvnw spring-boot:run
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
