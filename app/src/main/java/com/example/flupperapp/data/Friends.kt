package com.example.flupperapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friend(
    @PrimaryKey
    val id: Int,
    val name: String,
    val avatarUrl: String,
)