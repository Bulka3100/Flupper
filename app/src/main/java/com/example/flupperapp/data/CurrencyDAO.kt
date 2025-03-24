package com.example.flupperapp.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
//тут вопрос про корутины и ассинхронность
@Dao
interface CurrencyDAO {
    @Query("SELECT amount FROM currency WHERE id = 1")
    suspend fun getCurrency(): Int

    @Update
    suspend fun updateCurrency(currency: Currency)

    @Insert
    suspend fun insertCurrency(currency: Currency)
}