package com.example.flupperapp

import android.app.Application
import com.example.flupperapp.data.AppDatabase
import com.example.flupperapp.data.Currency
import com.example.flupperapp.data.CurrencyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//MyApplication.kt — это класс, который наследуется от Application. Он запускается один раз при старте приложения, ещё до того, как открывается MainActivity. Его задача — выполнить какие-то глобальные настройки, которые нужны всему приложению.
class MyApplication : Application() {
    private val db by lazy { AppDatabase.getInstance(this) }
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        scope.launch {
            val currencyDAO: CurrencyDAO = db.currencyDAO()
            try {
                val currentAmount = currencyDAO.getCurrency()
                if (currentAmount == 0) { // Если запись пуста или не существует
                    val initialCurrency = Currency(amount = 0)
                    currencyDAO.insertCurrency(initialCurrency)
                }
            } catch (e: Exception) {
                // Если записи нет, создаём новую
                val initialCurrency = Currency(amount = 0)
                currencyDAO.insertCurrency(initialCurrency)
            }
        }
    }

    fun getDatabase(): AppDatabase {
        return db
    }
}

//!!! разобрать что я тут написали получше