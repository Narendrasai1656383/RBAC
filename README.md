VRV Security - JWT Authentication
Overview
This project is a simple Spring Boot application that handles user authentication and authorization using JWT (JSON Web Tokens). Users can register, log in, and access specific resources based on their roles (e.g., Admin, User). It uses Spring Security to secure the application and MySQL as the database.

Key Features
User Registration: Users can create an account with a username and password.
Login: Users can log in to the system with their credentials.
JWT Authentication: After login, users receive a JWT token for secure access.
Roles: Each user has a role, such as USER, to define access levels.
Technologies Used
Spring Boot: For creating the application.
Spring Security: To secure the application and handle authentication.
JWT: For token-based user authentication.
Spring Data JPA: For database operations.
MySQL: For storing user and role data.
BCrypt: For securely hashing passwords.
Project Structure
Controllers: Handles user registration, login, and token generation.
Models: Represents User and Role entities.
Repositories: Provides database operations for User and Role.
Utils: Contains logic for generating and validating JWT tokens.
Config: Configures Spring Security and password encoding.
How It Works
User Model:

Stores user data (username, password, and role).
The role is a reference to the Role model.
java
Copy code
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
Role Model:

Represents roles like USER, ADMIN.
Stored in the roles table in the database.
java
Copy code
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
JWT Utility:

JwtUtil generates JWT tokens when a user logs in.
It also provides methods to validate and extract the username from the token.
java
Copy code
@Component
public class JwtUtil {
    private final String secret = "your_jwt_secret_key";
    public String generateToken(String username) { /* ... */ }
    public String getUsernameFromToken(String token) { /* ... */ }
    public boolean validateToken(String token) { /* ... */ }
}
Authentication Controller:

AuthController handles user registration and login.
On registration, the user's password is hashed and saved in the database.
On login, it verifies credentials and returns a JWT token if valid.
java
Copy code
@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/register")
    public String register(@RequestBody User user) { /* ... */ }
    @PostMapping("/login")
    public String login(@RequestBody User user) { /* ... */ }
}
Security Configuration:

Configures password encryption using BCrypt.
java
Copy code
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
Database Setup
MySQL: Make sure you have MySQL installed and running.
Tables: You will need two tables: users and roles.
Configuration: Set the database credentials in application.properties.
Running the Application
Set up the database: Create a vrv_security database in MySQL.
Configure application: In application.properties, add your MySQL database credentials.
Build the project: Use Maven to build the project:
bash
Copy code
mvn clean install
Run the project:
bash
Copy code
mvn spring-boot:run
Conclusion
This project implements a simple, secure authentication system using JWT and Role-Based Access Control (RBAC). It allows users to register, log in, and access resources based on their assigned roles. The project uses Spring Boot, Spring Security, and MySQL to provide secure and flexible authentication.
