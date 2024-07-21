package com.alexkarav.exchangerate.ui.components

import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen

@Serializable
data class ResultScreenArgs(
    val currencyToConvert: String,
    val initialCurrencyValue: Int
)