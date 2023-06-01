package ru.rsu.app.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskStatusDto(
    val name: String,
    val description: String? = null
)
