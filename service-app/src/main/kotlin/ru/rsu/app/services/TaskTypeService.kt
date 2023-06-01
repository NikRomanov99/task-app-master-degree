package ru.rsu.app.services

import ru.rsu.app.database.generated.tables.pojos.TaskType
import ru.rsu.app.model.dto.TaskTypeDto
import ru.rsu.app.model.dto.response.TaskTypeResponseDto
import ru.rsu.app.repositories.TaskTypeRepository

class TaskTypeService(private val taskTypeRepository: TaskTypeRepository) {
    suspend fun findListAllTaskType(): List<TaskTypeResponseDto> {
        return taskTypeRepository.selectTaskTypes()
    }

    suspend fun findTaskTypeById(id: Long): List<TaskTypeResponseDto> {
        return taskTypeRepository.selectTaskTypeById(id)
    }

    suspend fun addTaskType(body: TaskTypeDto) {
        val taskType = TaskType(name = body.name, description = body.description)
        taskTypeRepository.insertTaskType(taskType)
    }

    suspend fun updateTaskType(body: TaskTypeDto, id: Long) {
        val taskTypeInDb = findTaskTypeById(id)
        if (taskTypeInDb.isNotEmpty()) {
            val taskType = TaskType(id = id, name = body.name, description = body.description)
            taskTypeRepository.updateTaskType(taskType)
        }
    }

    suspend fun deleteTaskType(id: Long) {
        taskTypeRepository.deleteTaskTypeById(id)
    }
}