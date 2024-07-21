package com.alexkarav.exchangerate.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkarav.exchangerate.domain.repo.ExchangeRateRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeResultViewModel @Inject constructor(private val repo: ExchangeRateRepositoryImpl): ViewModel() {
    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState = _loadingState.asStateFlow()

    fun loadCurrencyRate(currency: String) {
        viewModelScope.launch {
            val result = repo.getExchangeRateForCurrency(currency)
            if(result.isSuccess) {
                _loadingState.value = LoadingState.Success(result.getOrNull()!!)
            }
            else {
                println(result.exceptionOrNull()?.message)
                _loadingState.value = LoadingState.Error("Произошла ошибка при загрузке данных с сервера")
            }
        }
    }
}