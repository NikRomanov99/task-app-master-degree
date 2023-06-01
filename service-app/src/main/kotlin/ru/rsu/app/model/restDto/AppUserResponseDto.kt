package ru.rsu.app.model.restDto

import kotlinx.serialization.Serializable

@Serializable
data class AppUserResponseDto(
    val id: Long,
    val name: String,
    val surname: String,
    val roleDescription: String,
    val description: String? = "",
    val tgUserName: String? = ""
)
