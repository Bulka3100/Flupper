package com.example.flupperapp.data_classes

data class TaskData(
    val text: String,
    val taskId: String,
    val description: String,
    var isComplited: Boolean = false,
    var reward: Int = 10,
)