package com.example.flupperapp

import com.example.flupperapp.data_classes.TaskData

object STUB {
    val globalTasksList: List<TaskData> = listOf(
        TaskData("Main Task 1", "5", "Main description 1", false, 20),
        TaskData("Main Task 1", "5", "Main description 1", false, 20),
        TaskData("Main Task 1", "5", "Main description 1", false, 20)
    )

    val tasksList: MutableList<TaskData> = mutableListOf(
        TaskData("Drink 2l Water", "1", "be healthy!", false, 10),
        TaskData("Wash teeth", "2", "be clean", false, 5),
        TaskData("Drink 2l Water", "3", "be healthy", false, 10),
        TaskData("Drink 2l Water", "4", "be healthy", false, 10)
    )

    fun showTasks(): List<TaskData> {
        return tasksList
    }

    fun showGlobalTasks(): List<TaskData> {
        return globalTasksList

    }

    fun addDailyTask(task: TaskData) {
        tasksList.add(task)
    }
}
