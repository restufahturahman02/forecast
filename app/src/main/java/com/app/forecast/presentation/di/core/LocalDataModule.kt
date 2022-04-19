package com.app.forecast.presentation.di.core

import com.app.forecast.data.db.CityDao
import com.app.forecast.data.repository.datasource.ForecastLocalDataSource
import com.app.forecast.data.repository.datasourcelmpl.ForecastLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideForecastLocalDataSource(cityDao: CityDao): ForecastLocalDataSource {
        return ForecastLocalDataSourceImpl(cityDao)
    }

}