package ru.rsu.app.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject
import ru.rsu.app.model.dto.TaskTypeDto
import ru.rsu.app.services.TaskTypeService

fun Route.taskTypeRouting() {
    val taskTypeService by inject<TaskTypeService>()

    route("/taskType") {
        get("") {
            val result = taskTypeService.findListAllTaskType()
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val result = taskTypeService.findTaskTypeById(id)
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result.first())
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post {
            val taskType = call.receive<TaskTypeDto>()
            taskTypeService.addTaskType(taskType)
            call.respond(HttpStatusCode.OK)
        }
        patch("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val taskType = call.receive<TaskTypeDto>()
            taskTypeService.updateTaskType(taskType, id)
            call.respond(HttpStatusCode.OK)
        }
        delete("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            taskTypeService.deleteTaskType(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}