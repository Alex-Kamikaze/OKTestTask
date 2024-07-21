package com.alexkarav.exchangerate.domain.repo

import com.alexkarav.exchangerate.data.api.ExchangeRateApi
import com.alexkarav.exchangerate.data.repo.ExchangeRateRepository
import com.alexkarav.exchangerate.domain.models.CurrencyRate
import com.alexkarav.exchangerate.util.parseCurrencyResponse
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(private val api: ExchangeRateApi): ExchangeRateRepository {
    override suspend fun getExchangeRateForCurrency(currencyName: String): Result<CurrencyRate> {
        return try {
            val result = api.getExchangeRates(currencies = currencyName)
            if(result.data.isEmpty()) {
                Result.failure(IOException("Некорректный ответ сервера"))
            } else {
                Result.success(parseCurrencyResponse(result)[0])
            }
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}