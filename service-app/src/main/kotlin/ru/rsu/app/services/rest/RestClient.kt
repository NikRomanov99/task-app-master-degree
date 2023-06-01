package ru.rsu.app.services.rest

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import ru.rsu.app.model.restDto.AppUserResponseDto
import ru.rsu.app.utils.PropertyConfigUtils

class RestClient(private val propertyConfigUtils: PropertyConfigUtils) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getUserInfoByTgUserName(tgUserName: String): AppUserResponseDto? {
        val client = HttpClient(CIO)
        var appUserData: AppUserResponseDto? = null
        runBlocking {
            try {
                val response = client.get("${propertyConfigUtils.getEquipmentServiceUrl()}/appUser/tgUserName/$tgUserName")
                if (HttpStatusCode.OK == response.status) {
                    val user = response.call.response.body<String>()
                    appUserData = Json.decodeFromString(AppUserResponseDto.serializer(), user)
                } else {
                    logger.error("Error from task-service: ${response.responseTime} ${response.status}")
                }
            } finally {
                client.close()
            }
        }
        return appUserData
    }

    fun getUserInfoById(id: Long): AppUserResponseDto? {
        val client = HttpClient(CIO)
        var userDirector: AppUserResponseDto? = null
        runBlocking {
            try {
                val response = client.get("${propertyConfigUtils.getEquipmentServiceUrl()}/appUser/$id")
                if (HttpStatusCode.OK == response.status) {
                    val user = response.call.response.body<String>()
                    userDirector = Json.decodeFromString(AppUserResponseDto.serializer(), user)
                } else {
                    logger.error("Error from task-service: ${response.responseTime} ${response.status}")
                }
            } finally {
                client.close()
            }
        }
        return userDirector
    }
}