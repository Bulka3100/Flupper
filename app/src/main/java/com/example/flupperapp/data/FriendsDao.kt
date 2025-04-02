package com.example.flupperapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendsDao {
    @Insert
    fun insert(friend: Friend)

    @Query("SELECT * FROM friends")
    fun getAllFriends(): Flow<List<Friend>>

    @Delete
    fun delete(friend: Friend)
}