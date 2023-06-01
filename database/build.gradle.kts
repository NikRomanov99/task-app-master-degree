import com.typesafe.config.ConfigFactory
import nu.studer.gradle.jooq.JooqEdition

val ktor_version: String = "2.2.4"
val kotlin_version: String = "1.8.20"
val logback_version: String = "1.2.11"
val postgres_driver_version: String = "42.5.1"
val jooq_version: String = "3.18.3"

plugins {
    id("com.avast.gradle.docker-compose") version "0.16.12"
    id("org.flywaydb.flyway") version "9.8.1"
    id("nu.studer.jooq") version "8.2"
}

dependencies {
    api("com.zaxxer:HikariCP:5.0.1")
    api("org.jooq:jooq:$jooq_version")
    api("org.flywaydb:flyway-core:9.16.1")
    api("org.postgresql:postgresql:$postgres_driver_version")

    jooqGenerator("org.postgresql:postgresql:$postgres_driver_version")
}

//Default config for build
val defaultConfig = ConfigFactory.parseMap(
    mapOf(
        "ktor.datasource.username" to "postgres",
        "ktor.datasource.password" to "Tolstochok3000",
        "ktor.datasource.jdbcUrl" to "jdbc:postgresql://localhost:5432/task-app",
        "ktor.datasource.schema" to "public"
    )
)
val config = ConfigFactory.systemEnvironment().withFallback(defaultConfig)
val urlDb = config.getString("ktor.datasource.jdbcUrl")
val schemaDB = config.getString("ktor.datasource.schema")
val usernameDb = config.getString("ktor.datasource.username")
val passwordDb = config.getString("ktor.datasource.password")

flyway {
    url = urlDb
    user = usernameDb
    password = passwordDb
}

jooq {
    version.set(jooq_version)
    edition.set(JooqEdition.OSS)
    configurations {
        configurations {
            create("main") {
                //Set false after generate all entities for fix problem with docker
                generateSchemaSourceOnCompilation.set(false)
                jooqConfiguration.apply {
                    jdbc.apply {
                        driver = "org.postgresql.Driver"
                        url = urlDb
                        user = usernameDb
                        password = passwordDb
                    }
                    generator.apply {
                        name = "org.jooq.codegen.KotlinGenerator"
                        database.apply {
                            name = "org.jooq.meta.postgres.PostgresDatabase"
                            excludes = "flyway_schema_history"
                            inputSchema = "public"
                        }
                        generate.apply {
                            isRelations = true
                            isDeprecated = false
                            isRecords = true
                            isPojos = true
                            isPojosEqualsAndHashCode = true
                            isDaos = true
                        }
                        target.apply {
                            packageName = "ru.rsu.app.database.generated"
                            directory = "src/main/kotlin"
                        }
                        strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                    }
                }
            }
        }
    }
}