package com.alexkarav.exchangerate.di

import com.alexkarav.exchangerate.data.api.ExchangeRateApi
import com.alexkarav.exchangerate.domain.repo.ExchangeRateRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideExchangeApi(): ExchangeRateApi {
        return Retrofit.Builder()
            .baseUrl(ExchangeRateApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRepository(api: ExchangeRateApi): ExchangeRateRepositoryImpl {
        return ExchangeRateRepositoryImpl(api)
    }
}