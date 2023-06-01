/*
 * This file is generated by jOOQ.
 */
package ru.rsu.app.database.generated.keys


import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal

import ru.rsu.app.database.generated.tables.TaskInfo
import ru.rsu.app.database.generated.tables.TaskStatus
import ru.rsu.app.database.generated.tables.TaskType
import ru.rsu.app.database.generated.tables.records.TaskInfoRecord
import ru.rsu.app.database.generated.tables.records.TaskStatusRecord
import ru.rsu.app.database.generated.tables.records.TaskTypeRecord



// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val TASK_INFO_PKEY: UniqueKey<TaskInfoRecord> = Internal.createUniqueKey(TaskInfo.TASK_INFO, DSL.name("task_info_pkey"), arrayOf(TaskInfo.TASK_INFO.ID), true)
val TASK_STATUS_PKEY: UniqueKey<TaskStatusRecord> = Internal.createUniqueKey(TaskStatus.TASK_STATUS, DSL.name("task_status_pkey"), arrayOf(TaskStatus.TASK_STATUS.ID), true)
val TASK_TYPE_PKEY: UniqueKey<TaskTypeRecord> = Internal.createUniqueKey(TaskType.TASK_TYPE, DSL.name("task_type_pkey"), arrayOf(TaskType.TASK_TYPE.ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val TASK_INFO__FK_TASK_STATUS_ID: ForeignKey<TaskInfoRecord, TaskStatusRecord> = Internal.createForeignKey(TaskInfo.TASK_INFO, DSL.name("fk_task_status_id"), arrayOf(TaskInfo.TASK_INFO.R_TASK_STATUS_ID), ru.rsu.app.database.generated.keys.TASK_STATUS_PKEY, arrayOf(TaskStatus.TASK_STATUS.ID), true)
val TASK_INFO__FK_TASK_TYPE_ID: ForeignKey<TaskInfoRecord, TaskTypeRecord> = Internal.createForeignKey(TaskInfo.TASK_INFO, DSL.name("fk_task_type_id"), arrayOf(TaskInfo.TASK_INFO.R_TASK_TYPE_ID), ru.rsu.app.database.generated.keys.TASK_TYPE_PKEY, arrayOf(TaskType.TASK_TYPE.ID), true)
