package ru.rsu.app.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import ru.rsu.app.database.generated.tables.daos.TaskTypeDao
import ru.rsu.app.database.generated.tables.pojos.TaskType
import ru.rsu.app.database.generated.tables.references.TASK_TYPE
import ru.rsu.app.model.dto.response.TaskTypeResponseDto

class TaskTypeRepository(private val dslContext: DSLContext) {
    private val taskTypeDao = TaskTypeDao(dslContext.configuration())

    suspend fun selectTaskTypes(): List<TaskTypeResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_TYPE.ID,
                TASK_TYPE.NAME,
                TASK_TYPE.DESCRIPTION
            )
                .from(TASK_TYPE)
                .fetchInto(TaskTypeResponseDto::class.java)
        }

    suspend fun selectTaskTypeById(id: Long): List<TaskTypeResponseDto> =
        withContext(Dispatchers.IO) {
            dslContext.select(
                TASK_TYPE.ID,
                TASK_TYPE.NAME,
                TASK_TYPE.DESCRIPTION
            )
                .from(TASK_TYPE)
                .where(TASK_TYPE.ID.eq(id))
                .fetchInto(TaskTypeResponseDto::class.java)
        }

    suspend fun insertTaskType(taskType: TaskType) =
        withContext(Dispatchers.IO) {
            taskTypeDao.insert(taskType)
        }

    suspend fun updateTaskType(taskType: TaskType) =
        withContext(Dispatchers.IO) {
            taskTypeDao.update(taskType)
        }

    suspend fun deleteTaskTypeById(id: Long) =
        withContext(Dispatchers.IO) {
            dslContext.delete(TASK_TYPE).where(TASK_TYPE.ID.eq(id))
        }
}