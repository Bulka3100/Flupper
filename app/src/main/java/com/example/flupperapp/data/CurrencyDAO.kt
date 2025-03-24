package com.example.flupperapp.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
//тут вопрос про корутины и ассинхронность
// не надо же писать suspend fun?
@Dao
interface CurrencyDAO {
    @Query("SELECT amount FROM currency WHERE id = 1") fun getCurrency(): Int

    @Update
   fun updateCurrency(currency: Currency)

    @Insert
    fun insertCurrency(currency: Currency)
}
//update suspend выдает ошибку видимо тут автоматом стоит