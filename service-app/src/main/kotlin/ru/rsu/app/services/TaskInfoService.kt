package ru.rsu.app.services

import kotlinx.serialization.json.Json.Default.encodeToString
import org.apache.kafka.clients.producer.ProducerRecord
import ru.rsu.app.database.generated.tables.pojos.TaskInfo
import ru.rsu.app.model.dto.TaskInfoDto
import ru.rsu.app.model.dto.response.TaskInfoResponseDto
import ru.rsu.app.model.dto.response.TaskInfoResponseForTg
import ru.rsu.app.model.enums.TaskStatus
import ru.rsu.app.model.msg.LastInspectionMsg
import ru.rsu.app.model.msg.NewTaskMsg
import ru.rsu.app.model.msg.TaskChangeStatusInfo
import ru.rsu.app.model.restDto.AppUserResponseDto
import ru.rsu.app.repositories.TaskInfoRepository
import ru.rsu.app.services.kafka.KafkaClient
import ru.rsu.app.services.rest.RestClient
import ru.rsu.app.utils.PropertyConfigUtils
import java.time.LocalDateTime

class TaskInfoService(
    private val taskInfoRepository: TaskInfoRepository,
    private val propertyConfigUtils: PropertyConfigUtils,
    private val kafkaClient: KafkaClient,
    private val restClient: RestClient

) {
    suspend fun findListAllTaskInfo(): List<TaskInfoResponseDto> {
        return taskInfoRepository.selectTaskInfos()
    }

    suspend fun findTaskInfoById(id: Long): List<TaskInfoResponseDto> {
        return taskInfoRepository.selectTaskInfoById(id)
    }

    suspend fun findAllTaskInfoByTgUserName(tgUserName: String): List<TaskInfoResponseForTg> {
        val userInfo = restClient.getUserInfoByTgUserName(tgUserName)
        if (userInfo != null) {
            val userExecutorTasks = taskInfoRepository.selectTaskInfoByUserExecutorId(userInfo.id)
            val result = buildTaskInfoResponseForTgList(userExecutorTasks)
            if (result.isNotEmpty()) {
                return result
            }
        }
        return emptyList()
    }

    suspend fun addTaskInfo(body: TaskInfoDto) {
        val appUser = TaskInfo(
            name = body.name,
            rTaskTypeId = body.taskTypeId,
            rTaskStatusId = body.taskStatusId,
            taskDescription = body.taskDescription,
            rUserExecutorId = body.userExecutorId,
            rUserDirectorId = body.userDirectorId,
            rAvailableEquipmentId = body.availableEquipmentId,
            startTaskDate = LocalDateTime.parse(body.startTaskDate.toString()),
            finishTaskDate = LocalDateTime.parse(body.finishTaskDate.toString())
        )
        taskInfoRepository.insertTaskInfo(appUser)

        val producer = kafkaClient.createKafkaProducer()
        val topicName = propertyConfigUtils.getNewTaskTopicName()
        val lastTaskId = taskInfoRepository.selectLastTaskId()
        val msg = buildMsgForNewTask(lastTaskId!!)
        producer.send(ProducerRecord(topicName, msg))
    }

    suspend fun updateTaskInfo(body: TaskInfoDto, id: Long) {
        val taskInfoInDb = findTaskInfoById(id)
        if (taskInfoInDb.isNotEmpty()) {
            val taskType = TaskInfo(
                id = id,
                name = body.name,
                rTaskTypeId = body.taskTypeId,
                rTaskStatusId = body.taskStatusId,
                taskDescription = body.taskDescription,
                rUserExecutorId = body.userExecutorId,
                rUserDirectorId = body.userDirectorId,
                rAvailableEquipmentId = body.availableEquipmentId,
                startTaskDate = LocalDateTime.parse(body.startTaskDate.toString()),
                finishTaskDate = LocalDateTime.parse(body.finishTaskDate.toString())
            )
            taskInfoRepository.updateTaskInfo(taskType)
        }
    }

    suspend fun updateTaskStatusInfo(taskChangeStatusInfo: TaskChangeStatusInfo) {
        val taskInfo = taskInfoRepository.selectTaskInfoById(taskChangeStatusInfo.taskId)
        if (taskInfo.isNotEmpty()) {
            val task = taskInfo.first()
            taskInfoRepository.updateTaskInfoStatus(task.id, TaskStatus.COMPLETED.statusCode)

            val producer = kafkaClient.createKafkaProducer()
            val topicName = propertyConfigUtils.getUpdateLastInspectionDateTopicName()
            val msg = buildMsgForInspection(
                task.availableEquipmentId!!,
                taskChangeStatusInfo.tgUserName,
                taskChangeStatusInfo.lastInspectionDate
            )
            producer.send(ProducerRecord(topicName, msg))
        }
    }

    suspend fun deleteTaskInfo(id: Long) {
        taskInfoRepository.deleteTaskInfoById(id)
    }

    private fun buildMsgForInspection(equipmentId: Long, tgUserName: String, lastInspectionDate: String): String {
        val lastInspectionMsg = LastInspectionMsg(equipmentId, tgUserName, lastInspectionDate)
        return encodeToString(LastInspectionMsg.serializer(), lastInspectionMsg)
    }

    private fun buildMsgForNewTask(taskId: Long, tgUserName: String = "pepperoni_dog"): String {
        val newTaskMsg = NewTaskMsg(taskId, tgUserName)
        return encodeToString(NewTaskMsg.serializer(), newTaskMsg)
    }

    private fun buildTaskInfoResponseForTgList(tasks: List<TaskInfoResponseDto>): List<TaskInfoResponseForTg> {
        val resultList = arrayListOf<TaskInfoResponseForTg>()
        for (task in tasks) {
            var userDirectorName = ""
            if (task.userDirectorId != null) {
                val userDirector = restClient.getUserInfoById(task.userDirectorId)
                if (userDirector != null) {
                    userDirectorName = "${userDirector.name} ${userDirector.surname}"
                }
            }

            val taskForTg = TaskInfoResponseForTg(
                id = task.id,
                name = task.name,
                taskTypeName = task.taskTypeName,
                taskStatusName = task.taskStatusName,
                userDirectorName = userDirectorName,
                availableEquipmentId = task.availableEquipmentId,
                startTaskDate = task.startTaskDate,
                finishTaskDate = task.finishTaskDate
            )
            resultList.add(taskForTg)
        }
        return resultList
    }
}