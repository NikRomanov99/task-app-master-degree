package ru.rsu.app.model.enums

enum class TaskStatus(val statusCode: Long) {
    DELIVERED(1),
    IN_WORK(2),
    COMPLETED(3),
    CHECK_RESULT(4),
    RETURNED(5)
}