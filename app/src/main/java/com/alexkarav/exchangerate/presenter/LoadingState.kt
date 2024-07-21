package com.alexkarav.exchangerate.presenter

import com.alexkarav.exchangerate.domain.models.CurrencyRate

sealed class LoadingState {
    //Пустое состояние - заглушка чтобы не срабатывало лишних модалок
    data object Idle: LoadingState()
    data object Loading: LoadingState()
    data class Error(val message: String): LoadingState()
    data class Success(val result: CurrencyRate?): LoadingState()
}