package com.app.forecast.presentation.di.core


import com.app.forecast.domain.repository.ForecastRepository
import com.app.forecast.domain.usecase.GetForecastUseCase
import com.app.forecast.domain.usecase.CityUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetForecastUseCase(forecastRepository: ForecastRepository): GetForecastUseCase {
        return GetForecastUseCase(forecastRepository)
    }
    @Provides
    fun provideCityUseCase(forecastRepository: ForecastRepository): CityUseCase {
        return CityUseCase(forecastRepository)
    }

}