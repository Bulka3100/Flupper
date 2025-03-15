package com.example.flupperapp

import com.example.flupperapp.data_classes.TaskData

// зачем тут делать object Stub
object STUB {
    val tasksList: List<TaskData> = listOf(
        TaskData("Drink 2l Water", "1", "be healthy!"),
        TaskData("Wash teeth", "2", "be clean"),
        TaskData("Drink 2l Water", "3", "be healthy"),
        TaskData("Drink 2l Water", "4", "be healthy"),
    )

    fun showTasks(): List<TaskData> {
        return tasksList
    }
}