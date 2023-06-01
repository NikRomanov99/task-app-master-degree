package ru.rsu.app.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject
import ru.rsu.app.model.dto.TaskInfoDto
import ru.rsu.app.model.dto.response.StandardResponseDto
import ru.rsu.app.services.TaskInfoService

fun Route.taskInfoRouting() {
    val taskInfoService by inject<TaskInfoService>()

    route("/taskInfo") {
        get("") {
            val result = taskInfoService.findListAllTaskInfo()
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound, StandardResponseDto(false, HttpStatusCode.NotFound.value))
            }
        }
        get("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val result = taskInfoService.findTaskInfoById(id)
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result.first())
            } else {
                call.respond(HttpStatusCode.NotFound, StandardResponseDto(false, HttpStatusCode.NotFound.value))
            }
        }
        get("/tgUserName/{tgUserName?}") {
            val tgUserName = call.parameters.getOrFail("tgUserName")
            val result = taskInfoService.findAllTaskInfoByTgUserName(tgUserName)
            if (result.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound, StandardResponseDto(false, HttpStatusCode.NotFound.value))
            }
        }
        post {
            val taskInfo = call.receive<TaskInfoDto>()
            taskInfoService.addTaskInfo(taskInfo)
            call.respond(HttpStatusCode.OK, StandardResponseDto(true, HttpStatusCode.OK.value))
        }
        patch("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            val taskInfo = call.receive<TaskInfoDto>()
            taskInfoService.updateTaskInfo(taskInfo, id)
            call.respond(HttpStatusCode.OK, StandardResponseDto(true, HttpStatusCode.OK.value))
        }
        delete("/{id?}") {
            val id = call.parameters.getOrFail("id").toLong()
            taskInfoService.deleteTaskInfo(id)
            call.respond(HttpStatusCode.OK, StandardResponseDto(true, HttpStatusCode.OK.value))
        }
    }
}