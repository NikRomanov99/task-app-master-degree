package ru.rsu.app

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject
import ru.rsu.app.plugins.configureKoin
import ru.rsu.app.plugins.configureRouting
import ru.rsu.app.plugins.configureSerialization
import ru.rsu.app.services.kafka.KafkaConsumer

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()

    val kafkaConsumer by inject<KafkaConsumer>()
    launch { kafkaConsumer.initConsumers() }
}
