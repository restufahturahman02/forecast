package com.app.forecast.presentation.di.core

import com.app.forecast.data.api.ForecastService
import com.app.forecast.data.repository.datasource.ForecastRemoteDatasource
import com.app.forecast.data.repository.datasourcelmpl.ForecastRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {
    @Singleton
    @Provides
    fun provideForecastRemoteDataSource(forecastService: ForecastService): ForecastRemoteDatasource {
        return ForecastRemoteDataSourceImpl(
            forecastService, apiKey
        )
    }
}