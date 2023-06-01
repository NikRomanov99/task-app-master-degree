package ru.rsu.app.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class StandardResponseDto(val SUCCESS: Boolean, val CODE: Int)
