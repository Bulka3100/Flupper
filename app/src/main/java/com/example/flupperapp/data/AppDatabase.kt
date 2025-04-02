package com.example.flupperapp.data


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //    почему тут все абстрактное и функция currencyDao так странно определена
    abstract fun currencyDAO(): CurrencyDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }
    }
}