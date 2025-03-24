package com.example.flupperapp

import android.app.Application
import android.util.Log
import com.example.flupperapp.data.AppDatabase
import com.example.flupperapp.data.Currency
import com.example.flupperapp.data.CurrencyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {
    private val db by lazy { AppDatabase.getInstance(this) }
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        scope.launch {
            val currencyDAO: CurrencyDAO = db.currencyDAO()
            try {
                val currentAmount = currencyDAO.getCurrency()
                Log.d("MyApplication", "Current currency amount: $currentAmount")
            } catch (e: Exception) {
                Log.e("MyApplication", "Error initializing currency: ${e.message}")
                // Вставляем новую запись, но не задаём id вручную
                val initialCurrency = Currency(amount = 0) // Room сам сгенерирует id
                currencyDAO.insertCurrency(initialCurrency)
                Log.d("MyApplication", "Inserted initial currency with amount: 0")
            }
        }
    }

    fun getDatabase(): AppDatabase {
        return db
    }
}