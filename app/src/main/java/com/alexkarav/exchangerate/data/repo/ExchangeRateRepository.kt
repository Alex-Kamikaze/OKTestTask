package com.alexkarav.exchangerate.data.repo

import com.alexkarav.exchangerate.domain.models.CurrencyRate

interface ExchangeRateRepository {
    suspend fun getExchangeRateForCurrency(currencyName: String): Result<CurrencyRate>
}