package ru.rsu.app.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.rsu.app.routes.taskInfoRouting
import ru.rsu.app.routes.taskStatusRouting
import ru.rsu.app.routes.taskTypeRouting

fun Application.configureRouting() {
    routing {
        taskTypeRouting()
        taskInfoRouting()
        taskStatusRouting()
    }
}