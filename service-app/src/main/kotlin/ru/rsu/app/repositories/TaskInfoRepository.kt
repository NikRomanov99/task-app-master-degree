package ru.rsu.app.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.impl.DSL
import ru.rsu.app.database.generated.tables.daos.TaskInfoDao
import ru.rsu.app.database.generated.tables.pojos.TaskInfo
import ru.rsu.app.database.generated.tables.references.TASK_INFO
import ru.rsu.app.database.generated.tables.references.TASK_STATUS
import ru.rsu.app.database.generated.tables.references.TASK_TYPE
import ru.rsu.app.model.dto.response.TaskInfoResponseDto

class TaskInfoRepository(private val dslContext: DSLContext) {
    private val taskInfoDao = TaskInfoDao(dslContext.configuration())

    suspend fun selectTaskInfos(): List<TaskInfoResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_INFO.ID,
                TASK_INFO.NAME,
                TASK_TYPE.NAME.`as`("taskTypeName"),
                TASK_STATUS.NAME.`as`("taskStatusName"),
                TASK_INFO.TASK_DESCRIPTION,
                TASK_INFO.R_USER_EXECUTOR_ID.`as`("userExecutorId"),
                TASK_INFO.R_USER_DIRECTOR_ID.`as`("userDirectorId"),
                TASK_INFO.R_AVAILABLE_EQUIPMENT_ID.`as`("availableEquipmentId"),
                TASK_INFO.START_TASK_DATE,
                TASK_INFO.FINISH_TASK_DATE
            )
                .from(TASK_INFO)
                .leftJoin(TASK_TYPE).on(TASK_INFO.R_TASK_TYPE_ID.eq(TASK_TYPE.ID))
                .leftJoin(TASK_STATUS).on(TASK_INFO.R_TASK_STATUS_ID.eq(TASK_STATUS.ID))
                .fetchInto(TaskInfoResponseDto::class.java)
        }

    suspend fun selectTaskInfoById(id: Long): List<TaskInfoResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_INFO.ID,
                TASK_INFO.NAME,
                TASK_TYPE.NAME.`as`("taskTypeName"),
                TASK_STATUS.NAME.`as`("taskStatusName"),
                TASK_INFO.TASK_DESCRIPTION,
                TASK_INFO.R_USER_EXECUTOR_ID.`as`("userExecutorId"),
                TASK_INFO.R_USER_DIRECTOR_ID.`as`("userDirectorId"),
                TASK_INFO.R_AVAILABLE_EQUIPMENT_ID.`as`("availableEquipmentId"),
                TASK_INFO.START_TASK_DATE,
                TASK_INFO.FINISH_TASK_DATE
            )
                .from(TASK_INFO)
                .leftJoin(TASK_TYPE).on(TASK_INFO.R_TASK_TYPE_ID.eq(TASK_TYPE.ID))
                .leftJoin(TASK_STATUS).on(TASK_INFO.R_TASK_STATUS_ID.eq(TASK_STATUS.ID))
                .where(TASK_INFO.ID.eq(id))
                .fetchInto(TaskInfoResponseDto::class.java)
        }

    suspend fun selectTaskInfoByUserExecutorId(userId: Long): List<TaskInfoResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_INFO.ID,
                TASK_INFO.NAME,
                TASK_TYPE.NAME.`as`("taskTypeName"),
                TASK_STATUS.NAME.`as`("taskStatusName"),
                TASK_INFO.TASK_DESCRIPTION,
                TASK_INFO.R_USER_EXECUTOR_ID.`as`("userExecutorId"),
                TASK_INFO.R_USER_DIRECTOR_ID.`as`("userDirectorId"),
                TASK_INFO.R_AVAILABLE_EQUIPMENT_ID.`as`("availableEquipmentId"),
                TASK_INFO.START_TASK_DATE,
                TASK_INFO.FINISH_TASK_DATE
            )
                .from(TASK_INFO)
                .leftJoin(TASK_TYPE).on(TASK_INFO.R_TASK_TYPE_ID.eq(TASK_TYPE.ID))
                .leftJoin(TASK_STATUS).on(TASK_INFO.R_TASK_STATUS_ID.eq(TASK_STATUS.ID))
                .where(TASK_INFO.R_USER_EXECUTOR_ID.eq(userId))
                .fetchInto(TaskInfoResponseDto::class.java)
        }

    suspend fun selectLastTaskId(): Long? =
        withContext(Dispatchers.IO) {
            dslContext.select(DSL.max(TASK_INFO.ID)).from(TASK_INFO).fetchOne()?.get(DSL.max(TASK_INFO.ID))
        }


    suspend fun updateTaskInfoStatus(id: Long, statusCode: Long) {
        withContext(Dispatchers.IO) {
            dslContext.update(TASK_INFO)
                .set(TASK_INFO.R_TASK_STATUS_ID, statusCode)
                .where(TASK_INFO.ID.eq(id)).execute()
        }
    }

    suspend fun insertTaskInfo(taskInfo: TaskInfo) =
        withContext(Dispatchers.IO) {
            taskInfoDao.insert(taskInfo)
        }

    suspend fun updateTaskInfo(taskInfo: TaskInfo) =
        withContext(Dispatchers.IO) {
            taskInfoDao.update(taskInfo)
        }

    suspend fun deleteTaskInfoById(id: Long) =
        withContext(Dispatchers.IO) {
            dslContext.delete(TASK_TYPE).where(TASK_TYPE.ID.eq(id))
        }
}