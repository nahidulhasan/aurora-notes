# Aurora Notes - Full Java 21 Project

This is a complete Ktor-based backend example configured to use Java 21 (jvmToolchain 21).

## Run locally

1. Start Postgres (docker):
```
docker run --name aurora-postgres -e POSTGRES_DB=aurora -e POSTGRES_USER=aurora -e POSTGRES_PASSWORD=aurora -p 5432:5432 -d postgres:15
```

2. Build & run:
```
./gradlew run
```

3. Endpoints:
- POST /api/auth/login  -> get JWT
- GET/POST /api/notes   -> requires Authorization: Bearer <token>
# aurora-notes
