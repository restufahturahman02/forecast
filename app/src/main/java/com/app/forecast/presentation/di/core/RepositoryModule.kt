package com.app.forecast.presentation.di.core


import com.app.forecast.data.repository.ForecastRepositoryImpl
import com.app.forecast.data.repository.datasource.ForecastLocalDataSource
import com.app.forecast.data.repository.datasource.ForecastRemoteDatasource
import com.app.forecast.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRemoteDatasource: ForecastRemoteDatasource,
        forecastLocalDataSource: ForecastLocalDataSource
    ): ForecastRepository {
        return ForecastRepositoryImpl(
            forecastRemoteDatasource,
            forecastLocalDataSource
        )
    }

}