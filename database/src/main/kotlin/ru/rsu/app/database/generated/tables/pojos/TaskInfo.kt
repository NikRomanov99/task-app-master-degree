/*
 * This file is generated by jOOQ.
 */
package ru.rsu.app.database.generated.tables.pojos


import java.io.Serializable
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class TaskInfo(
    var id: Long? = null,
    var name: String? = null,
    var taskDescription: String? = null,
    var rTaskTypeId: Long? = null,
    var rTaskStatusId: Long? = null,
    var rUserExecutorId: Long? = null,
    var rUserDirectorId: Long? = null,
    var rAvailableEquipmentId: Long? = null,
    var startTaskDate: LocalDateTime? = null,
    var finishTaskDate: LocalDateTime? = null
): Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (this::class != other::class)
            return false
        val o: TaskInfo = other as TaskInfo
        if (this.id == null) {
            if (o.id != null)
                return false
        }
        else if (this.id != o.id)
            return false
        if (this.name == null) {
            if (o.name != null)
                return false
        }
        else if (this.name != o.name)
            return false
        if (this.taskDescription == null) {
            if (o.taskDescription != null)
                return false
        }
        else if (this.taskDescription != o.taskDescription)
            return false
        if (this.rTaskTypeId == null) {
            if (o.rTaskTypeId != null)
                return false
        }
        else if (this.rTaskTypeId != o.rTaskTypeId)
            return false
        if (this.rTaskStatusId == null) {
            if (o.rTaskStatusId != null)
                return false
        }
        else if (this.rTaskStatusId != o.rTaskStatusId)
            return false
        if (this.rUserExecutorId == null) {
            if (o.rUserExecutorId != null)
                return false
        }
        else if (this.rUserExecutorId != o.rUserExecutorId)
            return false
        if (this.rUserDirectorId == null) {
            if (o.rUserDirectorId != null)
                return false
        }
        else if (this.rUserDirectorId != o.rUserDirectorId)
            return false
        if (this.rAvailableEquipmentId == null) {
            if (o.rAvailableEquipmentId != null)
                return false
        }
        else if (this.rAvailableEquipmentId != o.rAvailableEquipmentId)
            return false
        if (this.startTaskDate == null) {
            if (o.startTaskDate != null)
                return false
        }
        else if (this.startTaskDate != o.startTaskDate)
            return false
        if (this.finishTaskDate == null) {
            if (o.finishTaskDate != null)
                return false
        }
        else if (this.finishTaskDate != o.finishTaskDate)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.id == null) 0 else this.id.hashCode())
        result = prime * result + (if (this.name == null) 0 else this.name.hashCode())
        result = prime * result + (if (this.taskDescription == null) 0 else this.taskDescription.hashCode())
        result = prime * result + (if (this.rTaskTypeId == null) 0 else this.rTaskTypeId.hashCode())
        result = prime * result + (if (this.rTaskStatusId == null) 0 else this.rTaskStatusId.hashCode())
        result = prime * result + (if (this.rUserExecutorId == null) 0 else this.rUserExecutorId.hashCode())
        result = prime * result + (if (this.rUserDirectorId == null) 0 else this.rUserDirectorId.hashCode())
        result = prime * result + (if (this.rAvailableEquipmentId == null) 0 else this.rAvailableEquipmentId.hashCode())
        result = prime * result + (if (this.startTaskDate == null) 0 else this.startTaskDate.hashCode())
        result = prime * result + (if (this.finishTaskDate == null) 0 else this.finishTaskDate.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("TaskInfo (")

        sb.append(id)
        sb.append(", ").append(name)
        sb.append(", ").append(taskDescription)
        sb.append(", ").append(rTaskTypeId)
        sb.append(", ").append(rTaskStatusId)
        sb.append(", ").append(rUserExecutorId)
        sb.append(", ").append(rUserDirectorId)
        sb.append(", ").append(rAvailableEquipmentId)
        sb.append(", ").append(startTaskDate)
        sb.append(", ").append(finishTaskDate)

        sb.append(")")
        return sb.toString()
    }
}
