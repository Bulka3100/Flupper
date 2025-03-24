package com.example.flupperapp.utils

import android.widget.TextView
import com.example.flupperapp.data.Currency
import com.example.flupperapp.data.CurrencyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyManager(
    private val currencyDAO: CurrencyDAO,
    private var currencyView: TextView? = null
) {
    private var amount: Int = 0
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            amount = currencyDAO.getCurrency()
            updateUI()
        }
    }

    fun awardCurrency(amountToAward: Int = 10) {
        amount += amountToAward
        val currency = Currency(amount = amount)
        scope.launch {
            currencyDAO.updateCurrency(currency)
            updateUI()
        }
    }

    fun setCurrencyView(view: TextView) {
        currencyView = view
        updateUI()
    }

    private fun updateUI() {
        CoroutineScope(Dispatchers.Main).launch {
            currencyView?.text = "Валюта: $amount"
        }
    }
}