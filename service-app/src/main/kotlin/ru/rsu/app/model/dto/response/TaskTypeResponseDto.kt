package ru.rsu.app.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TaskTypeResponseDto(
    val id: Long,
    var name: String,
    var description: String? = null
)
