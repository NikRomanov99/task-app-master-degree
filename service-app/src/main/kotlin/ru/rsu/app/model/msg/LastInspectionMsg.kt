package ru.rsu.app.model.msg

import kotlinx.serialization.Serializable

@Serializable
data class LastInspectionMsg(val equipmentId: Long, val tgUserName: String, val lastInspectionDate: String)
