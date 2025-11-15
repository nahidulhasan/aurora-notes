package com.aurora.notes.routes

import com.aurora.notes.controllers.AuthController
import com.aurora.notes.config.AppConfig
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

class AuthRoutes {

    fun register(r: Routing) {
        // Inject inside register() in the Routing context
        val controller by r.application.inject<AuthController>()
        val cfg by r.application.inject<AppConfig>()

        r.post("/auth/login") {
            val payload = call.receive<Map<String, String>>()
            val username = payload["username"] ?: ""
            val password = payload["password"] ?: ""

            val token = controller.login(username, password)
                ?: return@post call.respond(mapOf("error" to "invalid credentials"))

            call.respond(mapOf("token" to token))
        }
    }
}
