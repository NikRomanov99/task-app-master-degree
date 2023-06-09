package ru.rsu.app.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TaskInfoResponseDto (
    val id: Long,
    val name: String,
    val taskTypeName: String,
    val taskStatusName: String,
    val taskDescription: String? = null,
    val userExecutorId: Long? = null,
    val userDirectorId: Long? = null,
    val availableEquipmentId: Long? = null,
    val startTaskDate: String? = null,
    val finishTaskDate: String? = null
)