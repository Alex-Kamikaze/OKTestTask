package com.alexkarav.exchangerate.util

import com.alexkarav.exchangerate.data.api.models.CurrencyResponse
import com.alexkarav.exchangerate.domain.models.CurrencyRate

fun parseCurrencyResponse(response: CurrencyResponse): List<CurrencyRate> {
    return response.data.map { (currency, rate) ->
        CurrencyRate(currency, rate)
    }
}