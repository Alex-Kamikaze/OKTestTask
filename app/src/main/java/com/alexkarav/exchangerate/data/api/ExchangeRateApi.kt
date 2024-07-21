package com.alexkarav.exchangerate.data.api

import com.alexkarav.exchangerate.data.api.models.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeRateApi {

    @GET("latest")
    suspend fun getExchangeRates(@Query("apikey") apiKey: String = API_KEY, @Query("currencies") currencies: String, @Query("base_currency") baseCurrency: String = "RUB"): CurrencyResponse

    companion object {
        const val BASE_URL = "https://api.freecurrencyapi.com/v1/"
        const val API_KEY = "fca_live_wm8UpbEk1M5IvSJXRzhDYj1IQ89mqyv1mnDhJMPZ"
    }
}