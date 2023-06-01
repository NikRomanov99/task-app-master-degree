package ru.rsu.app.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import ru.rsu.app.database.generated.tables.daos.TaskStatusDao
import ru.rsu.app.database.generated.tables.pojos.TaskStatus
import ru.rsu.app.database.generated.tables.references.TASK_STATUS
import ru.rsu.app.model.dto.response.TaskStatusResponseDto

class TaskStatusRepository(private val dslContext: DSLContext) {
    private val taskStatusDao = TaskStatusDao(dslContext.configuration())

    suspend fun selectTaskStatuses(): List<TaskStatusResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_STATUS.ID,
                TASK_STATUS.NAME,
                TASK_STATUS.DESCRIPTION
            )
                .from(TASK_STATUS)
                .fetchInto(TaskStatusResponseDto::class.java)
        }

    suspend fun selectTaskStatusById(id: Long): List<TaskStatusResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_STATUS.ID,
                TASK_STATUS.NAME,
                TASK_STATUS.DESCRIPTION
            )
                .from(TASK_STATUS)
                .where(TASK_STATUS.ID.eq(id))
                .fetchInto(TaskStatusResponseDto::class.java)
        }

    suspend fun insertTaskStatus(taskType: TaskStatus) =
        withContext(Dispatchers.IO) {
            taskStatusDao.insert(taskType)
        }

    suspend fun updateTaskStatus(taskType: TaskStatus) =
        withContext(Dispatchers.IO) {
            taskStatusDao.update(taskType)
        }

    suspend fun deleteTaskStatusById(id: Long) =
        withContext(Dispatchers.IO) {
            dslContext.delete(TASK_STATUS).where(TASK_STATUS.ID.eq(id))
        }
}