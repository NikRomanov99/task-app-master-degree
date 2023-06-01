package ru.rsu.app.services

import ru.rsu.app.database.generated.tables.pojos.TaskStatus
import ru.rsu.app.model.dto.TaskStatusDto
import ru.rsu.app.model.dto.response.TaskStatusResponseDto
import ru.rsu.app.repositories.TaskStatusRepository

class TaskStatusService(private val taskStatusRepository: TaskStatusRepository) {
    suspend fun findListAllTaskStatus(): List<TaskStatusResponseDto> {
        return taskStatusRepository.selectTaskStatuses()
    }

    suspend fun findTaskStatusById(id: Long): List<TaskStatusResponseDto> {
        return taskStatusRepository.selectTaskStatusById(id)
    }

    suspend fun addTaskStatus(body: TaskStatusDto) {
        val taskStatus = TaskStatus(name = body.name, description = body.description)
        taskStatusRepository.insertTaskStatus(taskStatus)
    }

    suspend fun updateTaskStatus(body: TaskStatusDto, id: Long) {
        val taskStatusInDb = findTaskStatusById(id)
        if (taskStatusInDb.isNotEmpty()) {
            val taskStatus = TaskStatus(id = id, name = body.name, description = body.description)
            taskStatusRepository.updateTaskStatus(taskStatus)
        }
    }

    suspend fun deleteTaskStatus(id: Long) {
        taskStatusRepository.deleteTaskStatusById(id)
    }
}