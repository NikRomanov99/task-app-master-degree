package ru.rsu.app.services.kafka

import kotlinx.serialization.json.Json
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import ru.rsu.app.model.msg.TaskChangeStatusInfo
import ru.rsu.app.services.TaskInfoService
import ru.rsu.app.utils.PropertyConfigUtils
import java.time.Duration
import java.util.*

class KafkaConsumer(
    private val propertyConfigUtils: PropertyConfigUtils,
    private val taskInfoService: TaskInfoService
) {
    suspend fun initConsumers() {
        val kafkaProperty = propertyConfigUtils.getKafkaProperty()
        val config = Properties().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.bootstrapServer)
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperty.groupId)
        }

        val updateTaskStatusConsumer = KafkaConsumer<String, String>(config)
        updateTaskStatusConsumer.subscribe(listOf(propertyConfigUtils.getUpdateTaskStatusTopicName()))

        while (true) {
            val records: ConsumerRecords<String, String> =
                updateTaskStatusConsumer.poll(Duration.ofMillis(1000))
            for (record in records) {
                val value = record.value()
                val updateTaskStatusMsg = Json.decodeFromString(TaskChangeStatusInfo.serializer(), value)
                taskInfoService.updateTaskStatusInfo(updateTaskStatusMsg)
            }
        }

        closeConsumers(listOf(updateTaskStatusConsumer))
    }

    private fun closeConsumers(consumers: List<KafkaConsumer<String, String>>) {
        consumers.forEach { consumer -> consumer.close() }
    }
}