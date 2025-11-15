package com.aurora.notes

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

import com.aurora.notes.config.AppConfig
import com.aurora.notes.di.koinModule
import com.aurora.notes.repository.DatabaseFactory
import com.aurora.notes.routes.AuthRoutes
import com.aurora.notes.routes.NoteRoutes
import com.aurora.notes.security.JwtProvider

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    val config = AppConfig.fromConfig(
        HoconApplicationConfig(ConfigFactory.load())
    )

    install(CallLogging) { level = Level.INFO }
    install(ContentNegotiation) { json() }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(mapOf("error" to (cause.message ?: "unknown error")))
        }
    }

    DatabaseFactory.init(config)

    install(Koin) {
        modules(koinModule(config))
    }

    install(Authentication) {
        jwt("auth-jwt") {
            realm = config.jwt.realm
            verifier(JwtProvider.verifier(config.jwt))
            validate { cred ->
                val claim = cred.payload.getClaim("userId")
                val uid = if (!claim.isNull) claim.asInt() else null
                if (uid != null) com.aurora.notes.models.UserPrincipal(uid) else null
            }
        }
    }

    routing {
        get("/") {
            call.respond(mapOf("service" to "Aurora Notes API"))
        }

        route("/api") {
            // Pass the outer Routing receiver so register(...) gets a Routing (not a Route)
            AuthRoutes().register(this@routing)

            authenticate("auth-jwt") {
                // still register routes under the outer routing so routing types match
                NoteRoutes().register(this@routing)
            }

            get("/health") {
                call.respond(mapOf("status" to "ok"))
            }
        }
    }

}
