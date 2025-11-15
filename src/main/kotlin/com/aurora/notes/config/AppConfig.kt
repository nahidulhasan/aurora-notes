package com.aurora.notes.config

import io.ktor.server.config.*

data class JwtConfig(val secret: String, val issuer: String, val audience: String, val realm: String)

data class DbConfig(val jdbcUrl: String, val user: String, val password: String)

data class AppConfig(val jwt: JwtConfig, val db: DbConfig) {
    companion object {
        fun fromConfig(cfg: ApplicationConfig) = AppConfig(
            jwt = JwtConfig(
                secret = cfg.property("app.jwt.secret").getString(),
                issuer = cfg.property("app.jwt.issuer").getString(),
                audience = cfg.property("app.jwt.audience").getString(),
                realm = cfg.property("app.jwt.realm").getString()
            ),
            db = DbConfig(
                jdbcUrl = cfg.property("app.db.jdbcUrl").getString(),
                user = cfg.property("app.db.user").getString(),
                password = cfg.property("app.db.password").getString()
            )
        )
    }
}
