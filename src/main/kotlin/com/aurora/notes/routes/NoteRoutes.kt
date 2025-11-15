package com.aurora.notes.routes

import com.aurora.notes.controllers.NoteController
import com.aurora.notes.models.NoteDto
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

class NoteRoutes {

    fun register(r: Routing) {
        // Ktor 3 + Koin 3.5 → inject INSIDE the routing context
        val controller by r.application.inject<NoteController>()

        r.route("/notes") {

            get {
                call.respond(controller.list())
            }

            post {
                val body = call.receive<NoteDto>()
                call.respond(controller.create(body.title, body.content))
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(mapOf("error" to "invalid id"))

                val note = controller.get(id)
                    ?: return@get call.respond(mapOf("error" to "not found"))

                call.respond(note)
            }
        }
    }
}
