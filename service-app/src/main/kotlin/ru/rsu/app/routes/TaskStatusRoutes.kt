package ru.rsu.app.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject
import ru.rsu.app.model.dto.TaskStatusDto
import ru.rsu.app.services.TaskStatusService

fun Route.taskStatusRouting() {
    val taskStatusService by inject<TaskStatusService>()

    route("/taskStatus") {
        get("") {
            val result = taskStatusService.findListAllTaskStatus()
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val result = taskStatusService.findTaskStatusById(id)
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result.first())
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post {
            val taskStatus = call.receive<TaskStatusDto>()
            taskStatusService.addTaskStatus(taskStatus)
            call.respond(HttpStatusCode.OK)
        }
        patch("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val taskStatus = call.receive<TaskStatusDto>()
            taskStatusService.updateTaskStatus(taskStatus, id)
            call.respond(HttpStatusCode.OK)
        }
        delete("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            taskStatusService.deleteTaskStatus(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}