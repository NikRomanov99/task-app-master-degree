val ktor_version: String = "2.2.4"
val kotlin_version: String = "1.8.20"
val koin_version: String = "3.4.0"
val logback_version: String = "1.2.11"
val kafka_version:String = "3.4.0"

plugins {
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
    id("io.ktor.plugin") version "2.2.4"
}

group = "ru.rsu.app"
version = "0.0.1"


application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

apply(plugin = "application")
apply(plugin = "jacoco")

dependencies {
    implementation(project(":database"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

    //Ktor
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    //Koin
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    //LogBack
    implementation("ch.qos.logback:logback-classic:$logback_version")

    //Kafka
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("org.apache.kafka:kafka-clients:$kafka_version")

    //Testing
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
