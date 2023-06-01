package ru.rsu.app.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskInfoDto(
    val name: String,
    val taskTypeId: Long,
    val taskStatusId: Long,
    val taskDescription: String? = null,
    val userExecutorId: Long,
    val userDirectorId: Long,
    val availableEquipmentId: Long? = null,
    val startTaskDate: String? = null,
    val finishTaskDate: String? = null
)
