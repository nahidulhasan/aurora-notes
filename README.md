📘 Aurora Notes – Ktor Backend API

A lightweight, secure, and modular Notes Management backend built using Ktor, Koin, Exposed ORM, JWT Authentication, and PostgreSQL.
This project is designed as a clean Kotlin backend boilerplate for secure, scalable REST APIs.

🚀 Features

🔐 JWT Authentication (Login / Register)

📝 Notes CRUD API

🗄️ PostgreSQL database with Exposed ORM

⚙️ Dependency Injection using Koin

📦 Shadow JAR support

🧩 Clean module-based architecture

🛡️ Error handling with StatusPages

🔌 Structured routing (Auth + Notes)

📄 Configurable using application.conf

🏗 Tech Stack
| Layer             | Technology      |
| ----------------- | --------------- |
| Backend Framework | Ktor 3.3        |
| Language          | Kotlin (JVM 21) |
| Auth              | JWT             |
| DI                | Koin            |
| Database          | PostgreSQL      |
| ORM               | Exposed         |
| Connection Pool   | HikariCP        |
| Packaging         | Shadow JAR      |


📁 Project Structure

aurora-notes/
├── src/main/kotlin/com/aurora/notes
│   ├── Application.kt          # Main application entry
│   ├── config/                  # App + DB config loaders
│   ├── di/                      # Koin modules
│   ├── routes/                  # Routing for API endpoints
│   ├── repository/              # Database setup + DAOs
│   ├── models/                  # DTO + DB Models
│   ├── security/                # JWT provider
│   └── services/                # Business logic
├── src/main/resources
│   └── application.conf         # Environment configuration
├── build.gradle.kts             # Gradle configuration
└── README.md




▶️ Running Locally

1️⃣ Install dependencies
./gradlew clean build --refresh-dependencies

2️⃣ Start PostgreSQL

Make sure PostgreSQL is running and database exists:

createdb aurora_notes -U postgres

3️⃣ Run the server
./gradlew run


Server runs at:

http://localhost:8080

🔐 Authentication Endpoints
POST /api/auth/register
{
"email": "test@example.com",
"password": "secret"
}

POST /api/auth/login

Response:

{
"token": "<jwt-token>"
}


Use JWT in headers:

Authorization: Bearer <token>

📝 Notes API (Requires JWT)
GET /api/notes

Fetch all notes for user.

POST /api/notes
{
"title": "My Note",
"content": "Hello world!"
}

PUT /api/notes/{id}
{
"title": "Updated",
"content": "Updated content"
}

DELETE /api/notes/{id}
❤️ Health Check
GET /api/health

🚀 Deployment Guide
Deploy to Linux server
scp build/libs/aurora-notes-all.jar ubuntu@server:/app/
ssh ubuntu@server "java -jar /app/aurora-notes-all.jar"


📝 License

MIT License
