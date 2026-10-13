# MyNewStore Spring Boot API

MyNewStore is a Java REST API for a small online store. It provides customer registration and login, product endpoints, and order creation. It uses PostgreSQL for persistence and bearer JWTs to authenticate protected requests.

## Technology and architecture

| Area | Technology |
| --- | --- |
| Language | Java 25 |
| Framework | Spring Boot 4.1.1 |
| Build tool | Maven Wrapper 3.3.4, which downloads Maven 3.9.16 |
| Web/API | Spring MVC |
| Persistence | Spring Data JPA and Hibernate |
| Database | PostgreSQL, using the PostgreSQL JDBC driver |
| Authentication | Spring Security, OAuth 2.0 Resource Server support, and signed JWTs (HS256) |
| API documentation | Springdoc OpenAPI 3.1.0 (Swagger UI) |
| Other libraries | Lombok; Jakarta Bean Validation |
| Tests | Spring Boot JPA and MVC test dependencies, JUnit via Spring Boot |

The code uses a layered architecture:

- **Controllers** expose HTTP endpoints.
- **Services** implement product and order operations.
- **Repositories** access the database through Spring Data JPA.
- **Entities** represent users, products, orders, and order items.
- **DTOs** define request and token payloads.
- **Security configuration** sets up stateless bearer-token authentication and authorization.

## Requirements

- Java Development Kit (JDK) 25.
- Git.
- Visual Studio Code with the **Extension Pack for Java** (recommended).
- A Supabase account and a Supabase project with PostgreSQL enabled.
- Internet access on the first Maven Wrapper run so it can download Maven and dependencies.

You do not need to install Maven separately; the repository includes the Maven Wrapper.

## Clone the repository

In a terminal, run:

```sh
git clone https://github.com/gandala-hub/mynewstore-springboot-apis.git
cd mynewstore-springboot-apis
code .
```

If the `code` command is not available, open VS Code and select **File → Open Folder**, then choose the cloned `mynewstore-springboot-apis` directory.

## Create and configure a Supabase database

1. Sign up or sign in at [supabase.com](https://supabase.com/).
2. Select **New project**, choose an organization, and provide a project name, database password, and region. Save the database password somewhere secure; it is not your Supabase account password.
3. Wait for the project to finish provisioning. Supabase creates a PostgreSQL database named `postgres` for the project; you normally do not need to create a separate database for this application.
4. In the Supabase project dashboard, open **Connect** and select a PostgreSQL connection string. For local development, use the **Session pooler** connection details if direct connections are unavailable on your network. Note the host, port, database name, and username shown by Supabase. A pooler username commonly includes the project reference, such as `postgres.<project-ref>`; use the exact value shown in your dashboard.
5. Set the database password to the password you chose when creating the project. If you no longer know it, reset it in the project's database settings.

Configure the connection in the shell that you will use to start the app. Replace the example values with the values from **Connect**. For the JDBC URL, use the same host, port, and database name from the selected connection details, and append `?sslmode=require`:

```sh
export SPRING_DATASOURCE_URL='jdbc:postgresql://<host>:<port>/<database>?sslmode=require'
export SPRING_DATASOURCE_USERNAME='<username-from-supabase>'
export SPRING_DATASOURCE_PASSWORD='<your-supabase-database-password>'
```

Spring Boot maps these environment variables to `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password`, overriding the URL currently present in `src/main/resources/application.properties`.

The API also requires a Base64-encoded JWT signing key. Generate a fresh 256-bit key on macOS or Linux:

```sh
export JWT_SECRET_BASE64="$(openssl rand -base64 32)"
```

Keep the database password and JWT secret private. Do not commit them, paste them into source files, or share them. If you open a new terminal, set the environment variables again before running the application.

## Run from VS Code

1. Open the project folder in VS Code.
2. Confirm VS Code is using JDK 25. You can check the installed Java version in the integrated terminal:

   ```sh
   java -version
   ```

3. Open **Terminal → New Terminal**. Set the Supabase and JWT environment variables in that terminal using the commands above.
4. Start the API:

   ```sh
   ./mvnw spring-boot:run
   ```

   On Windows PowerShell, use:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

The API listens on `http://localhost:8080` by default. Keep the terminal running while using the API. Stop it with **Ctrl+C**.

You can also import the Maven project into VS Code and use the Java extension's Run/Debug controls. Make sure the process launched by VS Code receives `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, and `JWT_SECRET_BASE64`; variables set only in an unrelated terminal may not be available to the debugger.

## Build and test

Run these commands from the project root:

```sh
# Compile and run tests
./mvnw test

# Package the application
./mvnw package
```

The executable JAR is created under `target/`. Run it with:

```sh
java -jar target/mynewstore-0.0.1-SNAPSHOT.jar
```

Set the same database and JWT environment variables before starting the JAR.

## API endpoints

The default base URL is `http://localhost:8080`.

| Method | Path | Access | Purpose |
| --- | --- | --- | --- |
| `POST` | `/api/auth/register` | Public | Register a customer |
| `POST` | `/api/auth/login` | Public | Authenticate and receive a bearer token |
| `GET` | `/api/products` | Public | List products |
| `GET` | `/api/products/{id}` | Public | Get a product |
| `POST` | `/api/products` | Admin | Create a product |
| `PUT` | `/api/products/{id}` | Admin | Update a product |
| `DELETE` | `/api/products/{id}` | Admin | Delete a product |
| `POST` | `/api/orders` | Authenticated | Create an order |

Registration accepts an email and a password of at least eight characters:

```sh
curl -i http://localhost:8080/api/auth/register \
  -H 'Content-Type: application/json' \
  -d '{"email":"customer@example.com","password":"change-this-password"}'
```

Log in to receive a JWT:

```sh
curl -i http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"customer@example.com","password":"change-this-password"}'
```

For protected endpoints, send the returned token as:

```text
Authorization: Bearer <token>
```

Interactive API documentation is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html), and the OpenAPI document is at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).

## Database schema

The application uses Hibernate's `ddl-auto=update` setting to create or update tables for its JPA entities when it connects to the database. This is convenient for local development. For production, use a controlled database migration approach rather than relying on automatic schema updates.

## Troubleshooting

- **Database connection or authentication error:** Recheck the host, port, database name, username, password, and `sslmode=require` against the Supabase **Connect** details. Ensure the environment variables were set in the same terminal or process that starts the app.
- **JWT secret configuration error:** Ensure `JWT_SECRET_BASE64` is set and contains a valid Base64-encoded key. Generate one with `openssl rand -base64 32`.
- **Wrong Java version:** Check `java -version` and configure VS Code's Java runtime to use JDK 25.
- **Maven Wrapper permission error on macOS/Linux:** Run `chmod +x mvnw`, then retry `./mvnw spring-boot:run`.
