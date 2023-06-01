package ru.rsu.app.configs

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jooq.DSLContext
import org.koin.dsl.module
import ru.rsu.app.repositories.TaskInfoRepository
import ru.rsu.app.repositories.TaskStatusRepository
import ru.rsu.app.repositories.TaskTypeRepository
import ru.rsu.app.services.TaskInfoService
import ru.rsu.app.services.TaskStatusService
import ru.rsu.app.services.TaskTypeService
import ru.rsu.app.services.kafka.KafkaClient
import ru.rsu.app.services.kafka.KafkaConsumer
import ru.rsu.app.services.rest.RestClient
import ru.rsu.app.utils.PropertyConfigUtils

val appModule = module {
    single<DataSourceConfig> {
        val applicationConfig = HoconApplicationConfig(ConfigFactory.load())
        DataSourceConfig.createDataSourceConfig(applicationConfig)
    }

    single<HikariDataSource> {
        DataSourceConfig.createDataSource(get())
    }

    single<DSLContext> {
        val dataSourceConfig: DataSourceConfig = get()
        val dataSource: HikariDataSource = get()
        DataSourceConfig.flywayMigrate(dataSource, dataSourceConfig)
        DataSourceConfig.createDSLContext(dataSource, dataSourceConfig)
    }

    //Utils
    single<PropertyConfigUtils> { PropertyConfigUtils() }

    //Rest
    single<RestClient> { RestClient(get()) }

    //Repository for DI
    single<TaskTypeRepository> { TaskTypeRepository(get()) }
    single<TaskStatusRepository> { TaskStatusRepository(get()) }
    single<TaskInfoRepository> { TaskInfoRepository(get()) }


    //Service for DI
    single<TaskTypeService> { TaskTypeService(get()) }
    single<TaskStatusService> { TaskStatusService(get()) }
    single<TaskInfoService> { TaskInfoService(get(), get(), get(), get()) }

    //Kafka
    single<KafkaConsumer> { KafkaConsumer(get(), get()) }
    single<KafkaClient> { KafkaClient(get()) }
}