package ru.rsu.app.utils

import com.typesafe.config.ConfigFactory
import ru.rsu.app.model.utils.KafkaProperty

class PropertyConfigUtils {
    private val config = ConfigFactory.load()
    fun getKafkaProperty(): KafkaProperty {
        return KafkaProperty(
            config.getString("ktor.kafka.bootstrap-servers"),
            config.getString("ktor.kafka.key-serializer"),
            config.getString("ktor.kafka.value-serializer"),
            config.getString("ktor.kafka.key-deserializer"),
            config.getString("ktor.kafka.value-deserializer"),
            config.getString("ktor.kafka.group-id"),
            config.getStringList("ktor.kafka.topics")
        )
    }

    fun getUpdateTaskStatusTopicName(): String {
        val topics = config.getStringList("ktor.kafka.topics")
        if (!topics.isEmpty()) {
            return topics.first()
        }
        return String()
    }

    fun getUpdateLastInspectionDateTopicName(): String {
        val topics = config.getStringList("ktor.kafka.topics")
        if (!topics.isEmpty() && topics.size > 1) {
            return topics[1]
        }
        return String()
    }

    fun getNewTaskTopicName(): String {
        val topics = config.getStringList("ktor.kafka.topics")
        if (!topics.isEmpty() && topics.size > 1) {
            return topics[2]
        }
        return String()
    }

    fun getEquipmentServiceUrl() : String {
        return config.getString("ktor.og_equipment.equipmentServiceUrl")
    }
}