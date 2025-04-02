package com.example.flupperapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class AppDatabaseFriends : RoomDatabase() {
    abstract fun friendDao(): FriendsDao
}
object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null
    fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
        INSTANCE = instance
        instance
    }
}