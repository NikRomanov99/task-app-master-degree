package ru.rsu.app.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TaskStatusResponseDto(
    val id: Long,
    val name: String,
    val description: String? = null
)
